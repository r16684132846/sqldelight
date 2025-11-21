#ifndef NAPI_BINDINGS_H
#define NAPI_BINDINGS_H

#include <napi/native_api.h>

// N-API绑定函数声明
static napi_value SetDatabasePath(napi_env env, napi_callback_info info);

static napi_value GetDatabasePath(napi_env env, napi_callback_info info);

static napi_value CreateTestDriver(void);

#endif // NAPI_BINDINGS_H
