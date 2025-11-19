/*
 * Tencent is pleased to support the open source community by making ovCompose available.
 * Copyright (C) 2025 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import { AbilityConstant, ConfigurationConstant, UIAbility, Want } from '@kit.AbilityKit';
import { hilog } from '@kit.PerformanceAnalysisKit';
import { window } from '@kit.ArkUI';
import { initResourceManager } from 'libentry.so';
import { fileIo as fs } from '@kit.CoreFileKit';
import { BusinessError } from '@kit.BasicServicesKit';

const DOMAIN = 0x0000;

export default class EntryAbility extends UIAbility {
  onCreate(want: Want, launchParam: AbilityConstant.LaunchParam): void {
    this.context.getApplicationContext().setColorMode(ConfigurationConstant.ColorMode.COLOR_MODE_NOT_SET);
    initResourceManager(this.context.resourceManager)
    // 设置数据库路径供native层使用
    this.setupDatabasePath();

    hilog.info(DOMAIN, 'testTag', '%{public}s', 'Ability onCreate');
  }

  onDestroy(): void {
    hilog.info(DOMAIN, 'testTag', '%{public}s', 'Ability onDestroy');
  }

  onWindowStageCreate(windowStage: window.WindowStage): void {
    // Main window is created, set main page for this ability
    hilog.info(DOMAIN, 'testTag', '%{public}s', 'Ability onWindowStageCreate');

    windowStage.loadContent('pages/Index', (err) => {
      if (err.code) {
        hilog.error(DOMAIN, 'testTag', 'Failed to load the content. Cause: %{public}s', JSON.stringify(err));
        return;
      }
      hilog.info(DOMAIN, 'testTag', 'Succeeded in loading the content.');
    });
  }

  onWindowStageDestroy(): void {
    // Main window is destroyed, release UI related resources
    hilog.info(DOMAIN, 'testTag', '%{public}s', 'Ability onWindowStageDestroy');
  }

  onForeground(): void {
    // Ability has brought to foreground
    hilog.info(DOMAIN, 'testTag', '%{public}s', 'Ability onForeground');
  }

  onBackground(): void {
    // Ability has back to background
    hilog.info(DOMAIN, 'testTag', '%{public}s', 'Ability onBackground');
  }


  // 设置数据库路径并确保目录存在
  private async setupDatabasePath(): Promise<void> {
    try {
      // 使用正确的沙箱路径
      const dbDirectory = `${this.context.filesDir}/databases`;
      hilog.info(DOMAIN, 'testTag', 'dbDirectory: %{public}s', dbDirectory);
      const dbPath = `${dbDirectory}/my_database.db`;

      hilog.info(DOMAIN, 'testTag', 'dbPath: %{public}s', dbPath);

      // 确保目录存在
      const isDirExist = fs.accessSync(dbDirectory, fs.AccessModeType.EXIST);
      if (!isDirExist) {
        await fs.mkdir(dbDirectory, true);
        hilog.info(DOMAIN, 'testTag', 'Database directory created: %{public}s', dbDirectory);
      }

      // 检查目录是否可写
      try {
        const testFile = `${dbDirectory}/test.tmp`;

        //使用 fs.createWriteStream 创建写入流
        const stream = fs.createWriteStream(testFile);
        stream.write('test');
        stream.close();
        fs.unlinkSync(testFile);
        hilog.info(DOMAIN, 'testTag', 'Database directory is writable');
      } catch (error) {
        hilog.error(DOMAIN, 'testTag', 'Database directory is not writable: %{public}s', error.message);
      }

      // 将dbPath传递到native层
      setDatabasePath(dbPath);
    } catch (error) {
      const businessError = error as BusinessError;
      hilog.error(DOMAIN, 'testTag', 'Failed to setup database path. Error: %{public}s, Code: %{public}d',
        businessError.message, businessError.code);
    }
  }
}

// 定义native模块的接口
interface EntryNativeModule {
  setDatabasePath(path: string): void;

  getDatabasePath(): string;
}

// 声明 requireNapi 函数，使用明确的返回类型
declare function requireNapi(moduleName: string): EntryNativeModule | undefined;

// 实现setDatabasePath函数，通过NAPI调用native层
function setDatabasePath(dbPath: string) {
  // 通过native方法设置数据库路径
  try {
    hilog.info(DOMAIN, 'testTag', 'Starting to call native setDatabasePath function');
    // 使用明确的类型定义
    let nativeLibrary: EntryNativeModule = requireNapi('entry') as EntryNativeModule
    hilog.info(DOMAIN, 'testTag', 'Native library object: %{public}s', JSON.stringify(nativeLibrary));
    if (nativeLibrary && nativeLibrary.setDatabasePath) {
      hilog.info(DOMAIN, 'testTag', 'Successfully found native setDatabasePath function');
      nativeLibrary.setDatabasePath(dbPath)
      hilog.info(DOMAIN, 'testTag', 'Successfully set database path to: %{public}s', dbPath)
    } else {
      hilog.error(DOMAIN, 'testTag', 'Failed to set database path in native layer - function not found')
    }
  } catch (error) {
    hilog.error(DOMAIN, 'testTag', 'Error setting database path: %{public}s', error.message)
  }
}
