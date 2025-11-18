cmake_minimum_required(VERSION 3.5.0)
project(harmonyApp)

set(NATIVERENDER_ROOT_PATH ${CMAKE_CURRENT_SOURCE_DIR})

if (DEFINED PACKAGE_FIND_FILE)
    include(${PACKAGE_FIND_FILE})
endif ()

include_directories(${NATIVERENDER_ROOT_PATH}
        ${NATIVERENDER_ROOT_PATH}/include)

find_package(skikobridge)

add_library(entry SHARED napi_init.cpp)
target_link_libraries(entry PUBLIC libace_napi.z.so)
target_link_libraries(entry PUBLIC libdeviceinfo_ndk.z.so)
target_link_libraries(entry PUBLIC librawfile.z.so)
target_link_libraries(entry PUBLIC ${NATIVERENDER_ROOT_PATH}/../../../libs/arm64-v8a/libkn.so)
target_link_libraries(entry PUBLIC skikobridge::skikobridge)

# 添加包含路径
include_directories(
        ${CMAKE_CURRENT_SOURCE_DIR}/include
)

# 指定 SQLite 源码
add_library(sqlite3 SHARED sqlite3.c)

install(TARGETS sqlite3 LIBRARY DESTINATION lib)