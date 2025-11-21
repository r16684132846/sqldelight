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
import relationalStore from '@ohos.data.relationalStore';
import type { Context } from '@kit.AbilityKit';

const DOMAIN = 0x0000;

// 添加全局变量来存储实例引用
let instance: EntryAbility | null = null;

export default class EntryAbility extends UIAbility {
  private rdbStore: relationalStore.RdbStore | null = null;

  // 添加静态方法供外部调用
  static insertPerson(name: string, age: number, email: string,
    callback: (err: Error | null, rowId?: number) => void): void {
    if (instance) {
      instance.insertPerson(name, age, email, callback);
    } else {
      callback(new Error('EntryAbility instance not available'));
    }
  }

  static queryAllPersons(callback: (err: Error | null, resultSet?: relationalStore.ResultSet) => void): void {
    if (instance) {
      instance.queryAllPersons(callback);
    } else {
      callback(new Error('EntryAbility instance not available'));
    }
  }

  static updatePerson(id: number, name: string, age: number, email: string,
    callback: (err: Error | null, count?: number) => void): void {
    if (instance) {
      instance.updatePerson(id, name, age, email, callback);
    } else {
      callback(new Error('EntryAbility instance not available'));
    }
  }

  static deletePerson(id: number, callback: (err: Error | null, count?: number) => void): void {
    if (instance) {
      instance.deletePerson(id, callback);
    } else {
      callback(new Error('EntryAbility instance not available'));
    }
  }

  onCreate(want: Want, launchParam: AbilityConstant.LaunchParam): void {
    // 保存实例引用
    instance = this;
    this.context.getApplicationContext().setColorMode(ConfigurationConstant.ColorMode.COLOR_MODE_NOT_SET);
    initResourceManager(this.context.resourceManager)
    this.initDatabase();

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

  // 插入新记录
  public insertPerson(name: string, age: number, email: string,
    callback: (err: Error | null, rowId?: number) => void): void {
    if (!this.rdbStore) {
      callback(new Error('Database not initialized'));
      return;
    }

    const valuesBucket: relationalStore.ValuesBucket = {
      'name': name,
      'age': age,
      'email': email
    };

    this.rdbStore.insert('personmanager', valuesBucket, (err, rowId) => {
      if (err) {
        callback(err);
      } else {
        callback(null, rowId);
      }
    });
  }

  // 查询所有记录
  public queryAllPersons(callback: (err: Error | null, resultSet?: relationalStore.ResultSet) => void): void {
    if (!this.rdbStore) {
      callback(new Error('Database not initialized'));
      return;
    }

    const querySql = 'SELECT * FROM personmanager';
    this.rdbStore.querySql(querySql, [], (err, resultSet) => {
      if (err) {
        callback(err);
      } else {
        callback(null, resultSet);
      }
    });
  }

  // 根据ID查询记录
  public queryPersonById(id: number,
    callback: (err: Error | null, resultSet?: relationalStore.ResultSet) => void): void {
    if (!this.rdbStore) {
      callback(new Error('Database not initialized'));
      return;
    }

    const querySql = 'SELECT * FROM personmanager WHERE id = ?';
    this.rdbStore.querySql(querySql, [id], (err, resultSet) => {
      if (err) {
        callback(err);
      } else {
        callback(null, resultSet);
      }
    });
  }

  // 更新记录
  public updatePerson(id: number, name: string, age: number, email: string,
    callback: (err: Error | null, count?: number) => void): void {
    if (!this.rdbStore) {
      callback(new Error('Database not initialized'));
      return;
    }

    // 显式声明类型，避免类型推断错误
    const valuesBucket: relationalStore.ValuesBucket = {
      'name': name,
      'age': age,
      'email': email
    };

    const predicates = new relationalStore.RdbPredicates('personmanager');
    predicates.equalTo('id', id);

    // this.rdbStore.update('personmanager', valuesBucket, predicates, (err, count) => {
    //   if (err) {
    //     callback(err);
    //   } else {
    //     callback(null, count);
    //   }
    // });
  }

  // 删除记录
  public deletePerson(id: number, callback: (err: Error | null, count?: number) => void): void {
    if (!this.rdbStore) {
      callback(new Error('Database not initialized'));
      return;
    }

    const predicates = new relationalStore.RdbPredicates('personmanager');
    predicates.equalTo('id', id);

    this.rdbStore.delete(predicates, (err, count) => {
      if (err) {
        callback(err);
      } else {
        callback(null, count);
      }
    });
  }
  

  private initDatabase() {
    const context: Context = this.context;

    // 数据库配置：名称为 mydatabase.db
    const STORE_CONFIG: relationalStore.StoreConfig = {
      name: 'mydatabase.db',
      securityLevel: relationalStore.SecurityLevel.S1 // S1 表示加密级别（普通）
    };

    // 获取数据库实例
    relationalStore.getRdbStore(context, STORE_CONFIG, (err, store) => {
      if (err) {
        console.error('Failed to get RdbStore. Error: ' + JSON.stringify(err));
        return;
      }
      this.rdbStore = store;
      console.log('Successfully opened database: mydatabase.db');

      // 创建 personmanager 表
      const createTableSql = `
        CREATE TABLE IF NOT EXISTS personmanager (
          id INTEGER PRIMARY KEY AUTOINCREMENT,
          name TEXT NOT NULL,
          age INTEGER,
          email TEXT UNIQUE
        );
      `;

      store.executeSql(createTableSql, null, (err) => {
        if (err) {
          console.error('Failed to create table personmanager. Error: ' + JSON.stringify(err));
        } else {
          console.log('Table personmanager created or already exists.');

          //插入一条测试数据
          const valuesBucket: relationalStore.ValuesBucket = {
            'name': '张三',
            'age': 28,
            'email': 'zhangsan@example.com'
          };
          // 插入数据
          store.insert('personmanager', valuesBucket, (err, rowId) => {
            if (err) {
              console.error('Insert failed:', JSON.stringify(err));
            } else {
              console.log('Inserted row ID:', rowId);
            }
          });
        }
      });
    });
  }
}
