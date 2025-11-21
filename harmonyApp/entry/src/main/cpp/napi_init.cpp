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

#include "include/napiBindings.h"
#include "libkn_api.h"
#include "napi/native_api.h"
#include <cstdlib>
#include <rawfile/raw_file_manager.h>
#include <string>

static napi_env g_env = nullptr;
std::string g_dbPath = "";
static bool get_function(napi_value obj, const char *name, napi_value *fn) {
    napi_value v;
    if (napi_get_named_property(g_env, obj, name, &v) != napi_ok)
        return false;
    napi_valuetype t;
    if (napi_typeof(g_env, v, &t) != napi_ok)
        return false;
    if (t != napi_function)
        return false;
    *fn = v;
    return true;
}
extern "C" const char *sqlDelightTest(void) {
    if (!g_env)
        return "";
    napi_handle_scope scope;
    if (napi_open_handle_scope(g_env, &scope) != napi_ok)
        return "";

    napi_value g;
    if (napi_get_global(g_env, &g) != napi_ok) {
        napi_close_handle_scope(g_env, scope);
        return "";
    }
    napi_value fn;
    // ArkTS 侧需导出 globalThis.clipboard_getUnifiedPlainText(): string
    if (!get_function(g, "clipboard_getUnifiedPlainText", &fn)) {
        napi_close_handle_scope(g_env, scope);
        return "";
    }

    napi_value ret;
    if (napi_call_function(g_env, g, fn, 0, nullptr, &ret) != napi_ok) {
        napi_close_handle_scope(g_env, scope);
        return "";
    }

    // 提取字符串并复制到静态缓存
    static char *s_text = nullptr;
    if (s_text) {
        free(s_text);
        s_text = nullptr;
    }
    size_t len = 0;
    napi_get_value_string_utf8(g_env, ret, nullptr, 0, &len);
    s_text = (char *)malloc(len + 1);
    if (!s_text) {
        napi_close_handle_scope(g_env, scope);
        return "";
    }
    napi_get_value_string_utf8(g_env, ret, s_text, len + 1, &len);
    s_text[len] = '\0';

    napi_close_handle_scope(g_env, scope);
    return s_text;
}

static napi_value Add(napi_env env, napi_callback_info info) {
    size_t argc = 2;
    napi_value args[2] = {nullptr};

    napi_get_cb_info(env, info, &argc, args, nullptr, nullptr);

    napi_valuetype valuetype0;
    napi_typeof(env, args[0], &valuetype0);

    napi_valuetype valuetype1;
    napi_typeof(env, args[1], &valuetype1);

    double value0;
    napi_get_value_double(env, args[0], &value0);

    double value1;
    napi_get_value_double(env, args[1], &value1);

    napi_value sum;
    napi_create_double(env, value0 + value1, &sum);

    return sum;
}

// 回调数据结构
struct CallbackData {
    napi_env env;
    napi_ref callback_ref;
};

// 通过全局函数调用 EntryAbility 的方法
static bool call_entry_ability_function(const char *function_name, napi_value *args, size_t argc, napi_value *result) {
    if (!g_env)
        return false;

    napi_handle_scope scope;
    napi_open_handle_scope(g_env, &scope);

    // 获取全局对象
    napi_value global;
    if (napi_get_global(g_env, &global) != napi_ok) {
        napi_close_handle_scope(g_env, scope);
        return false;
    }

    // 获取 EntryAbility 对象
    napi_value entryAbility;
    if (napi_get_named_property(g_env, global, "EntryAbility", &entryAbility) != napi_ok) {
        // 尝试从 globalThis 获取
        napi_value globalThis;
        if (napi_get_global(g_env, &globalThis) == napi_ok) {
            if (napi_get_named_property(g_env, globalThis, "EntryAbility", &entryAbility) != napi_ok) {
                napi_close_handle_scope(g_env, scope);
                return false;
            }
        } else {
            napi_close_handle_scope(g_env, scope);
            return false;
        }
    }

    // 获取函数
    napi_value function;
    if (napi_get_named_property(g_env, entryAbility, function_name, &function) != napi_ok) {
        napi_close_handle_scope(g_env, scope);
        return false;
    }

    // 调用函数
    napi_value recv;
    napi_get_undefined(g_env, &recv);
    if (napi_call_function(g_env, recv, function, argc, args, result) != napi_ok) {
        napi_close_handle_scope(g_env, scope);
        return false;
    }

    napi_close_handle_scope(g_env, scope);
    return true;
}

// 插入人员信息
static napi_value InsertPerson(napi_env env, napi_callback_info info) {
    size_t argc = 4;
    napi_value args[4] = {nullptr};
    napi_get_cb_info(env, info, &argc, args, nullptr, nullptr);

    if (argc < 4) {
        napi_throw_error(env, nullptr, "Wrong number of arguments");
        return nullptr;
    }

    // 调用 EntryAbility.insertPerson
    napi_value result;
    if (!call_entry_ability_function("insertPerson", args, 4, &result)) {
        napi_throw_error(env, nullptr, "Failed to call EntryAbility.insertPerson");
        return nullptr;
    }

    return result;
}

// 查询所有人员信息
static napi_value QueryAllPersons(napi_env env, napi_callback_info info) {
    size_t argc = 1;
    napi_value args[1] = {nullptr};
    napi_get_cb_info(env, info, &argc, args, nullptr, nullptr);

    if (argc < 1) {
        napi_throw_error(env, nullptr, "Wrong number of arguments");
        return nullptr;
    }

    // 调用 EntryAbility.queryAllPersons
    napi_value result;
    if (!call_entry_ability_function("queryAllPersons", args, 1, &result)) {
        napi_throw_error(env, nullptr, "Failed to call EntryAbility.queryAllPersons");
        return nullptr;
    }

    return result;
}

// 更新人员信息
static napi_value UpdatePerson(napi_env env, napi_callback_info info) {
    size_t argc = 5;
    napi_value args[5] = {nullptr};
    napi_get_cb_info(env, info, &argc, args, nullptr, nullptr);

    if (argc < 5) {
        napi_throw_error(env, nullptr, "Wrong number of arguments");
        return nullptr;
    }

    // 调用 EntryAbility.updatePerson
    napi_value result;
    if (!call_entry_ability_function("updatePerson", args, 5, &result)) {
        napi_throw_error(env, nullptr, "Failed to call EntryAbility.updatePerson");
        return nullptr;
    }

    return result;
}

// 删除人员信息
static napi_value DeletePerson(napi_env env, napi_callback_info info) {
    size_t argc = 2;
    napi_value args[2] = {nullptr};
    napi_get_cb_info(env, info, &argc, args, nullptr, nullptr);

    if (argc < 2) {
        napi_throw_error(env, nullptr, "Wrong number of arguments");
        return nullptr;
    }

    // 调用 EntryAbility.deletePerson
    napi_value result;
    if (!call_entry_ability_function("deletePerson", args, 2, &result)) {
        napi_throw_error(env, nullptr, "Failed to call EntryAbility.deletePerson");
        return nullptr;
    }

    return result;
}

static napi_value MainArkUIViewController(napi_env env, napi_callback_info info) {
    return reinterpret_cast<napi_value>(MainArkUIViewController(env));
}

static napi_value InitResourceManager(napi_env env, napi_callback_info info) {
    size_t argc = 1;
    napi_value args[1] = {nullptr};
    napi_get_cb_info(env, info, &argc, args, nullptr, nullptr);

    auto manager = OH_ResourceManager_InitNativeResourceManager(env, args[0]);
    auto kt = libkn_symbols();
    kt->kotlin.root.com.tencent.compose.initResourceManager(manager);

    napi_value result;
    napi_create_int32(env, 0, &result);
    return result;
}

static napi_value NAPI_Global_MainArkUIViewController(napi_env env, napi_callback_info info) {
    return reinterpret_cast<napi_value>(MainArkUIViewController(env));
}
EXTERN_C_START
static napi_value Init(napi_env env, napi_value exports) {
    g_env = env;
    androidx_compose_ui_arkui_init(env, exports);
    napi_property_descriptor desc[] = {
        {"add", nullptr, Add, nullptr, nullptr, nullptr, napi_default, nullptr},
        // 添加数据库操作函数的绑定
        {"insertPerson", nullptr, InsertPerson, nullptr, nullptr, nullptr, napi_default, nullptr},
        {"queryAllPersons", nullptr, QueryAllPersons, nullptr, nullptr, nullptr, napi_default, nullptr},
        {"updatePerson", nullptr, UpdatePerson, nullptr, nullptr, nullptr, napi_default, nullptr},
        {"deletePerson", nullptr, DeletePerson, nullptr, nullptr, nullptr, napi_default, nullptr},
        {"initResourceManager", nullptr, InitResourceManager, nullptr, nullptr, nullptr, napi_default, nullptr},
        {"MainArkUIViewController", nullptr, MainArkUIViewController, nullptr, nullptr, nullptr, napi_default, nullptr},
        {"NAPI_Global_MainArkUIViewController", nullptr, NAPI_Global_MainArkUIViewController, nullptr, nullptr, nullptr,
         napi_default, nullptr},
    };
    napi_define_properties(env, exports, sizeof(desc) / sizeof(desc[0]), desc);
    return exports;
}
EXTERN_C_END

static napi_module demoModule = {
    .nm_version = 1,
    .nm_flags = 0,
    .nm_filename = "libkn.so",
    .nm_register_func = Init,
    .nm_modname = "entry",
    .nm_priv = ((void *)0),
    .reserved = {0},
};

extern "C" __attribute__((constructor)) void RegisterEntryModule(void) { napi_module_register(&demoModule); }
