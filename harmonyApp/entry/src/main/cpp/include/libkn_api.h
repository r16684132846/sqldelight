#ifndef KONAN_LIBKN_H
#define KONAN_LIBKN_H
#ifdef __cplusplus
extern "C" {
#endif
#ifdef __cplusplus
typedef bool            libkn_KBoolean;
#else
typedef _Bool           libkn_KBoolean;
#endif
typedef unsigned short     libkn_KChar;
typedef signed char        libkn_KByte;
typedef short              libkn_KShort;
typedef int                libkn_KInt;
typedef long long          libkn_KLong;
typedef unsigned char      libkn_KUByte;
typedef unsigned short     libkn_KUShort;
typedef unsigned int       libkn_KUInt;
typedef unsigned long long libkn_KULong;
typedef float              libkn_KFloat;
typedef double             libkn_KDouble;
typedef float __attribute__ ((__vector_size__ (16))) libkn_KVector128;
typedef void*              libkn_KNativePtr;
struct libkn_KType;
typedef struct libkn_KType libkn_KType;

typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Byte;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Short;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Int;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Long;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Float;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Double;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Char;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Boolean;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Unit;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_UByte;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_UShort;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_UInt;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_ULong;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_compose_Greeting;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_compose_db_MyDatabase;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_compose_db_PersonQueries;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_compose_db_MyDatabase_Companion;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_app_cash_sqldelight_db_SqlSchema;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_app_cash_sqldelight_db_SqlDriver;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_compose_db_Person;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Any;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_app_cash_sqldelight_Query;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Function2;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Function1;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_compose_sample_DatabaseHelper;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlinx_coroutines_flow_Flow;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlinx_serialization_json_Json;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_PieceData;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_androidx_compose_runtime_snapshots_SnapshotStateList;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_compose_sample_mainpage_Test1;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_compose_sample_mainpage_Test1_$serializer;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlinx_serialization_descriptors_SerialDescriptor;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Array;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlinx_serialization_encoding_Decoder;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlinx_serialization_encoding_Encoder;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_compose_sample_mainpage_Test1_Companion;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlinx_serialization_KSerializer;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_compose_sample_KsoupTest;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_compose_sample_Person;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_compose_sample_Person_$serializer;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_compose_sample_Person_Companion;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_compose_sample_XmlUtilTest;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_compose_sample_XmlUtilTest_Companion;

extern void androidx_compose_ui_arkui_ArkUIViewController_aboutToAppear(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_aboutToDisappear(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_cancelSyncRefresh(void* controllerRef, libkn_KInt refreshId);
extern void androidx_compose_ui_arkui_ArkUIViewController_dispatchHoverEvent(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_dispatchMouseEvent(void* controllerRef);
extern libkn_KBoolean androidx_compose_ui_arkui_ArkUIViewController_dispatchTouchEvent(void* controllerRef, void* nativeTouchEvent, libkn_KBoolean ignoreInteropView);
extern const char* androidx_compose_ui_arkui_ArkUIViewController_getId(void* controllerRef);
extern void* androidx_compose_ui_arkui_ArkUIViewController_getXComponentRender(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_keyboardWillHide(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_keyboardWillShow(void* controllerRef, libkn_KFloat keyboardHeight);
extern libkn_KBoolean androidx_compose_ui_arkui_ArkUIViewController_onBackPress(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_onFinalize(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_onFocusEvent(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_onFrame(void* controllerRef, libkn_KLong timestamp, libkn_KLong targetTimestamp);
extern void androidx_compose_ui_arkui_ArkUIViewController_onKeyEvent(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_onPageHide(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_onPageShow(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_onSurfaceChanged(void* controllerRef, libkn_KInt width, libkn_KInt height);
extern void androidx_compose_ui_arkui_ArkUIViewController_onSurfaceCreated(void* controllerRef, void* xcomponentPtr, libkn_KInt width, libkn_KInt height);
extern void androidx_compose_ui_arkui_ArkUIViewController_onSurfaceDestroyed(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_onSurfaceHide(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_onSurfaceShow(void* controllerRef);
extern libkn_KInt androidx_compose_ui_arkui_ArkUIViewController_requestSyncRefresh(void* controllerRef);
extern const char* androidx_compose_ui_arkui_ArkUIViewController_sendMessage(void* controllerRef, const char* type, const char* message);
extern void androidx_compose_ui_arkui_ArkUIViewController_setContext(void* controllerRef, void* context);
extern void androidx_compose_ui_arkui_ArkUIViewController_setEnv(void* controllerRef, void* env);
extern void androidx_compose_ui_arkui_ArkUIViewController_setId(void* controllerRef, const char* id);
extern void androidx_compose_ui_arkui_ArkUIViewController_setMessenger(void* controllerRef, void* messenger);
extern void androidx_compose_ui_arkui_ArkUIViewController_setRootView(void* controllerRef, void* backRootView, void* foreRootView, void* touchableRootView);
extern void androidx_compose_ui_arkui_ArkUIViewController_setUIContext(void* controllerRef, void* uiContext);
extern void androidx_compose_ui_arkui_ArkUIViewController_setXComponentRender(void* controllerRef, void* render);
extern void androidx_compose_ui_arkui_init(void* env, void* exports);
extern void* MainArkUIViewController(void* env);

typedef struct {
  /* Service functions. */
  void (*DisposeStablePointer)(libkn_KNativePtr ptr);
  void (*DisposeString)(const char* string);
  libkn_KBoolean (*IsInstance)(libkn_KNativePtr ref, const libkn_KType* type);
  libkn_kref_kotlin_Byte (*createNullableByte)(libkn_KByte);
  libkn_KByte (*getNonNullValueOfByte)(libkn_kref_kotlin_Byte);
  libkn_kref_kotlin_Short (*createNullableShort)(libkn_KShort);
  libkn_KShort (*getNonNullValueOfShort)(libkn_kref_kotlin_Short);
  libkn_kref_kotlin_Int (*createNullableInt)(libkn_KInt);
  libkn_KInt (*getNonNullValueOfInt)(libkn_kref_kotlin_Int);
  libkn_kref_kotlin_Long (*createNullableLong)(libkn_KLong);
  libkn_KLong (*getNonNullValueOfLong)(libkn_kref_kotlin_Long);
  libkn_kref_kotlin_Float (*createNullableFloat)(libkn_KFloat);
  libkn_KFloat (*getNonNullValueOfFloat)(libkn_kref_kotlin_Float);
  libkn_kref_kotlin_Double (*createNullableDouble)(libkn_KDouble);
  libkn_KDouble (*getNonNullValueOfDouble)(libkn_kref_kotlin_Double);
  libkn_kref_kotlin_Char (*createNullableChar)(libkn_KChar);
  libkn_KChar (*getNonNullValueOfChar)(libkn_kref_kotlin_Char);
  libkn_kref_kotlin_Boolean (*createNullableBoolean)(libkn_KBoolean);
  libkn_KBoolean (*getNonNullValueOfBoolean)(libkn_kref_kotlin_Boolean);
  libkn_kref_kotlin_Unit (*createNullableUnit)(void);
  libkn_kref_kotlin_UByte (*createNullableUByte)(libkn_KUByte);
  libkn_KUByte (*getNonNullValueOfUByte)(libkn_kref_kotlin_UByte);
  libkn_kref_kotlin_UShort (*createNullableUShort)(libkn_KUShort);
  libkn_KUShort (*getNonNullValueOfUShort)(libkn_kref_kotlin_UShort);
  libkn_kref_kotlin_UInt (*createNullableUInt)(libkn_KUInt);
  libkn_KUInt (*getNonNullValueOfUInt)(libkn_kref_kotlin_UInt);
  libkn_kref_kotlin_ULong (*createNullableULong)(libkn_KULong);
  libkn_KULong (*getNonNullValueOfULong)(libkn_kref_kotlin_ULong);

  /* User functions. */
  struct {
    struct {
      struct {
        struct {
          struct {
            struct {
              struct {
                void (*_Export_ArkUIViewController_aboutToAppear)(void* controllerRef);
                void (*_Export_ArkUIViewController_aboutToDisappear)(void* controllerRef);
                void (*_Export_ArkUIViewController_cancelSyncRefresh)(void* controllerRef, libkn_KInt refreshId);
                void (*_Export_ArkUIViewController_dispatchHoverEvent)(void* controllerRef);
                void (*_Export_ArkUIViewController_dispatchMouseEvent)(void* controllerRef);
                libkn_KBoolean (*_Export_ArkUIViewController_dispatchTouchEvent)(void* controllerRef, void* nativeTouchEvent, libkn_KBoolean ignoreInteropView);
                const char* (*_Export_ArkUIViewController_getId)(void* controllerRef);
                void* (*_Export_ArkUIViewController_getXComponentRender)(void* controllerRef);
                void (*_Export_ArkUIViewController_keyboardWillHide)(void* controllerRef);
                void (*_Export_ArkUIViewController_keyboardWillShow)(void* controllerRef, libkn_KFloat keyboardHeight);
                libkn_KBoolean (*_Export_ArkUIViewController_onBackPress)(void* controllerRef);
                void (*_Export_ArkUIViewController_onFinalize)(void* controllerRef);
                void (*_Export_ArkUIViewController_onFocusEvent)(void* controllerRef);
                void (*_Export_ArkUIViewController_onFrame)(void* controllerRef, libkn_KLong timestamp, libkn_KLong targetTimestamp);
                void (*_Export_ArkUIViewController_onKeyEvent)(void* controllerRef);
                void (*_Export_ArkUIViewController_onPageHide)(void* controllerRef);
                void (*_Export_ArkUIViewController_onPageShow)(void* controllerRef);
                void (*_Export_ArkUIViewController_onSurfaceChanged)(void* controllerRef, libkn_KInt width, libkn_KInt height);
                void (*_Export_ArkUIViewController_onSurfaceCreated)(void* controllerRef, void* xcomponentPtr, libkn_KInt width, libkn_KInt height);
                void (*_Export_ArkUIViewController_onSurfaceDestroyed)(void* controllerRef);
                void (*_Export_ArkUIViewController_onSurfaceHide)(void* controllerRef);
                void (*_Export_ArkUIViewController_onSurfaceShow)(void* controllerRef);
                libkn_KInt (*_Export_ArkUIViewController_requestSyncRefresh)(void* controllerRef);
                const char* (*_Export_ArkUIViewController_sendMessage)(void* controllerRef, const char* type, const char* message);
                void (*_Export_ArkUIViewController_setContext)(void* controllerRef, void* context);
                void (*_Export_ArkUIViewController_setEnv)(void* controllerRef, void* env);
                void (*_Export_ArkUIViewController_setId)(void* controllerRef, const char* id);
                void (*_Export_ArkUIViewController_setMessenger)(void* controllerRef, void* messenger);
                void (*_Export_ArkUIViewController_setRootView)(void* controllerRef, void* backRootView, void* foreRootView, void* touchableRootView);
                void (*_Export_ArkUIViewController_setUIContext)(void* controllerRef, void* uiContext);
                void (*_Export_ArkUIViewController_setXComponentRender)(void* controllerRef, void* render);
                void (*_Export_ArkUIViewInitializer_init)(void* env, void* exports);
              } arkui;
            } ui;
          } export_;
        } compose;
      } androidx;
      struct {
        struct {
          struct {
            struct {
              libkn_KType* (*_type)(void);
              libkn_kref_com_tencent_compose_Greeting (*Greeting)();
              const char* (*greet)(libkn_kref_com_tencent_compose_Greeting thiz);
            } Greeting;
            struct {
              struct {
                struct {
                  libkn_KType* (*_type)(void);
                  libkn_kref_com_tencent_compose_db_MyDatabase_Companion (*_instance)();
                  libkn_kref_app_cash_sqldelight_db_SqlSchema (*get_Schema)(libkn_kref_com_tencent_compose_db_MyDatabase_Companion thiz);
                  libkn_kref_com_tencent_compose_db_MyDatabase (*invoke)(libkn_kref_com_tencent_compose_db_MyDatabase_Companion thiz, libkn_kref_app_cash_sqldelight_db_SqlDriver driver);
                } Companion;
                libkn_KType* (*_type)(void);
                libkn_kref_com_tencent_compose_db_PersonQueries (*get_personQueries)(libkn_kref_com_tencent_compose_db_MyDatabase thiz);
              } MyDatabase;
              struct {
                libkn_KInt (*com_tencent_compose_db_composeApp_MyDatabaseImpl_Schema$stableprop_getter)();
              } composeApp;
              struct {
                libkn_KType* (*_type)(void);
                libkn_kref_com_tencent_compose_db_Person (*Person)(libkn_KLong id, const char* name, libkn_KLong age);
                libkn_KLong (*get_age)(libkn_kref_com_tencent_compose_db_Person thiz);
                libkn_KLong (*get_id)(libkn_kref_com_tencent_compose_db_Person thiz);
                const char* (*get_name)(libkn_kref_com_tencent_compose_db_Person thiz);
                libkn_KLong (*component1)(libkn_kref_com_tencent_compose_db_Person thiz);
                const char* (*component2)(libkn_kref_com_tencent_compose_db_Person thiz);
                libkn_KLong (*component3)(libkn_kref_com_tencent_compose_db_Person thiz);
                libkn_kref_com_tencent_compose_db_Person (*copy)(libkn_kref_com_tencent_compose_db_Person thiz, libkn_KLong id, const char* name, libkn_KLong age);
                libkn_KBoolean (*equals)(libkn_kref_com_tencent_compose_db_Person thiz, libkn_kref_kotlin_Any other);
                libkn_KInt (*hashCode)(libkn_kref_com_tencent_compose_db_Person thiz);
                const char* (*toString)(libkn_kref_com_tencent_compose_db_Person thiz);
              } Person;
              struct {
                libkn_KType* (*_type)(void);
                libkn_kref_com_tencent_compose_db_PersonQueries (*PersonQueries)(libkn_kref_app_cash_sqldelight_db_SqlDriver driver);
                void (*deleteAll)(libkn_kref_com_tencent_compose_db_PersonQueries thiz);
                void (*deletePerson)(libkn_kref_com_tencent_compose_db_PersonQueries thiz, libkn_KLong id);
                void (*insertPerson)(libkn_kref_com_tencent_compose_db_PersonQueries thiz, libkn_kref_kotlin_Long id, const char* name, libkn_KLong age);
                libkn_kref_app_cash_sqldelight_Query (*selectAll)(libkn_kref_com_tencent_compose_db_PersonQueries thiz);
                libkn_kref_app_cash_sqldelight_Query (*selectById)(libkn_kref_com_tencent_compose_db_PersonQueries thiz, libkn_KLong id);
                void (*updatePerson)(libkn_kref_com_tencent_compose_db_PersonQueries thiz, const char* name, libkn_KLong age, libkn_KLong id);
              } PersonQueries;
              libkn_KInt (*com_tencent_compose_db_Person$stableprop_getter)();
              libkn_KInt (*com_tencent_compose_db_PersonQueries$stableprop_getter)();
              libkn_KInt (*com_tencent_compose_db_Person$stableprop_getter_)();
              libkn_KInt (*com_tencent_compose_db_PersonQueries$stableprop_getter_)();
              libkn_KInt (*com_tencent_compose_db_Person$stableprop_getter__)();
              libkn_KInt (*com_tencent_compose_db_PersonQueries$stableprop_getter__)();
            } db;
            struct {
              struct {
                libkn_KType* (*_type)(void);
                libkn_kref_com_tencent_compose_sample_DatabaseHelper (*DatabaseHelper)(libkn_kref_com_tencent_compose_db_PersonQueries personQueries);
                libkn_kref_kotlinx_coroutines_flow_Flow (*getAllPeople)(libkn_kref_com_tencent_compose_sample_DatabaseHelper thiz);
                libkn_kref_com_tencent_compose_db_Person (*getPersonById)(libkn_kref_com_tencent_compose_sample_DatabaseHelper thiz, libkn_KLong id);
              } DatabaseHelper;
              struct {
                libkn_KInt (*com_tencent_compose_sample_data_DisplayItem$stableprop_getter)();
                libkn_KInt (*com_tencent_compose_sample_data_DisplaySection$stableprop_getter)();
              } data;
              struct {
                struct {
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_PieceData (*PieceData)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game game, libkn_KFloat velocity, libkn_KULong color);
                    libkn_KBoolean (*get_clicked)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_PieceData thiz);
                    void (*set_clicked)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_PieceData thiz, libkn_KBoolean set);
                    libkn_KULong (*get_color)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_PieceData thiz);
                    libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game (*get_game)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_PieceData thiz);
                    libkn_KFloat (*get_position)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_PieceData thiz);
                    void (*set_position)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_PieceData thiz, libkn_KFloat set);
                    libkn_KFloat (*get_velocity)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_PieceData thiz);
                    void (*click)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_PieceData thiz);
                    libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game (*component1)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_PieceData thiz);
                    libkn_KFloat (*component2)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_PieceData thiz);
                    libkn_KULong (*component3)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_PieceData thiz);
                    libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_PieceData (*copy)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_PieceData thiz, libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game game, libkn_KFloat velocity, libkn_KULong color);
                    libkn_KBoolean (*equals)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_PieceData thiz, libkn_kref_kotlin_Any other);
                    libkn_KInt (*hashCode)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_PieceData thiz);
                    const char* (*toString)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_PieceData thiz);
                    void (*update)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_PieceData thiz, libkn_KLong dt);
                  } PieceData;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game (*Game)();
                    libkn_KLong (*get_elapsed)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game thiz);
                    void (*set_elapsed)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game thiz, libkn_KLong set);
                    libkn_KBoolean (*get_finished)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game thiz);
                    void (*set_finished)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game thiz, libkn_KBoolean set);
                    libkn_KFloat (*get_height)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game thiz);
                    void (*set_height)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game thiz, libkn_KFloat set);
                    libkn_KFloat (*get_numBlocks)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game thiz);
                    void (*set_numBlocks)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game thiz, libkn_KFloat set);
                    libkn_KBoolean (*get_paused)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game thiz);
                    void (*set_paused)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game thiz, libkn_KBoolean set);
                    libkn_kref_androidx_compose_runtime_snapshots_SnapshotStateList (*get_pieces)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game thiz);
                    libkn_KInt (*get_score)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game thiz);
                    void (*set_score)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game thiz, libkn_KInt set);
                    libkn_KBoolean (*get_started)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game thiz);
                    void (*set_started)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game thiz, libkn_KBoolean set);
                    libkn_KFloat (*get_width)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game thiz);
                    void (*set_width)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game thiz, libkn_KFloat set);
                    void (*clicked)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game thiz, libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_PieceData piece);
                    void (*start)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game thiz);
                    void (*update)(libkn_kref_com_tencent_compose_sample_mainpage_sectionItem_Game thiz, libkn_KLong deltaTimeNanos);
                  } Game;
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_DropdownItem$stableprop_getter)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_Game$stableprop_getter)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_PieceData$stableprop_getter)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_DropdownItem$stableprop_getter_)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_Game$stableprop_getter_)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_PieceData$stableprop_getter_)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_DropdownItem$stableprop_getter__)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_Game$stableprop_getter__)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_PieceData$stableprop_getter__)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_DropdownItem$stableprop_getter___)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_Game$stableprop_getter___)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_PieceData$stableprop_getter___)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_DropdownItem$stableprop_getter____)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_Game$stableprop_getter____)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_PieceData$stableprop_getter____)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_DropdownItem$stableprop_getter_____)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_Game$stableprop_getter_____)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_PieceData$stableprop_getter_____)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_DropdownItem$stableprop_getter______)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_Game$stableprop_getter______)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_PieceData$stableprop_getter______)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_DropdownItem$stableprop_getter_______)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_Game$stableprop_getter_______)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_PieceData$stableprop_getter_______)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_DropdownItem$stableprop_getter________)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_Game$stableprop_getter________)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_PieceData$stableprop_getter________)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_DropdownItem$stableprop_getter_________)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_Game$stableprop_getter_________)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_PieceData$stableprop_getter_________)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_DropdownItem$stableprop_getter__________)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_Game$stableprop_getter__________)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_PieceData$stableprop_getter__________)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_DropdownItem$stableprop_getter___________)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_Game$stableprop_getter___________)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_PieceData$stableprop_getter___________)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_DropdownItem$stableprop_getter____________)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_Game$stableprop_getter____________)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_PieceData$stableprop_getter____________)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_DropdownItem$stableprop_getter_____________)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_Game$stableprop_getter_____________)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_PieceData$stableprop_getter_____________)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_DropdownItem$stableprop_getter______________)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_Game$stableprop_getter______________)();
                  libkn_KInt (*com_tencent_compose_sample_mainpage_sectionItem_PieceData$stableprop_getter______________)();
                } sectionItem;
                struct {
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_compose_sample_mainpage_Test1_$serializer (*_instance)();
                    libkn_kref_kotlinx_serialization_descriptors_SerialDescriptor (*get_descriptor)(libkn_kref_com_tencent_compose_sample_mainpage_Test1_$serializer thiz);
                    libkn_kref_kotlin_Array (*childSerializers)(libkn_kref_com_tencent_compose_sample_mainpage_Test1_$serializer thiz);
                    libkn_kref_com_tencent_compose_sample_mainpage_Test1 (*deserialize)(libkn_kref_com_tencent_compose_sample_mainpage_Test1_$serializer thiz, libkn_kref_kotlinx_serialization_encoding_Decoder decoder);
                    void (*serialize)(libkn_kref_com_tencent_compose_sample_mainpage_Test1_$serializer thiz, libkn_kref_kotlinx_serialization_encoding_Encoder encoder, libkn_kref_com_tencent_compose_sample_mainpage_Test1 value);
                  } $serializer;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_compose_sample_mainpage_Test1_Companion (*_instance)();
                    libkn_kref_kotlinx_serialization_KSerializer (*serializer)(libkn_kref_com_tencent_compose_sample_mainpage_Test1_Companion thiz);
                  } Companion;
                  libkn_KType* (*_type)(void);
                  libkn_kref_com_tencent_compose_sample_mainpage_Test1 (*Test1)();
                  const char* (*get_a)(libkn_kref_com_tencent_compose_sample_mainpage_Test1 thiz);
                  void (*set_a)(libkn_kref_com_tencent_compose_sample_mainpage_Test1 thiz, const char* set);
                } Test1;
                libkn_KInt (*com_tencent_compose_sample_mainpage_Test1$stableprop_getter)();
                libkn_KInt (*com_tencent_compose_sample_mainpage_Test1_$serializer$stableprop_getter)();
                libkn_kref_kotlinx_serialization_json_Json (*get_encodeJsonFormat)();
                libkn_KInt (*com_tencent_compose_sample_mainpage_Test1$stableprop_getter_)();
                libkn_KInt (*com_tencent_compose_sample_mainpage_Test1_$serializer$stableprop_getter_)();
                const char* (*testJson)();
                const char* (*testKsoup)();
                const char* (*testXmlUtil)();
                libkn_KInt (*com_tencent_compose_sample_mainpage_Test1$stableprop_getter__)();
                libkn_KInt (*com_tencent_compose_sample_mainpage_Test1_$serializer$stableprop_getter__)();
              } mainpage;
              struct {
                libkn_KType* (*_type)(void);
                libkn_kref_com_tencent_compose_sample_KsoupTest (*KsoupTest)();
                const char* (*runAllTests)(libkn_kref_com_tencent_compose_sample_KsoupTest thiz);
                const char* (*testKsoupElementSelection)(libkn_kref_com_tencent_compose_sample_KsoupTest thiz);
                const char* (*testKsoupHtmlCleaning)(libkn_kref_com_tencent_compose_sample_KsoupTest thiz);
                const char* (*testKsoupHtmlParsing)(libkn_kref_com_tencent_compose_sample_KsoupTest thiz);
              } KsoupTest;
              struct {
                struct {
                  libkn_KType* (*_type)(void);
                  libkn_kref_com_tencent_compose_sample_Person_$serializer (*_instance)();
                  libkn_kref_kotlinx_serialization_descriptors_SerialDescriptor (*get_descriptor)(libkn_kref_com_tencent_compose_sample_Person_$serializer thiz);
                  libkn_kref_kotlin_Array (*childSerializers)(libkn_kref_com_tencent_compose_sample_Person_$serializer thiz);
                  libkn_kref_com_tencent_compose_sample_Person (*deserialize)(libkn_kref_com_tencent_compose_sample_Person_$serializer thiz, libkn_kref_kotlinx_serialization_encoding_Decoder decoder);
                  void (*serialize)(libkn_kref_com_tencent_compose_sample_Person_$serializer thiz, libkn_kref_kotlinx_serialization_encoding_Encoder encoder, libkn_kref_com_tencent_compose_sample_Person value);
                } $serializer;
                struct {
                  libkn_KType* (*_type)(void);
                  libkn_kref_com_tencent_compose_sample_Person_Companion (*_instance)();
                  libkn_kref_kotlinx_serialization_KSerializer (*serializer)(libkn_kref_com_tencent_compose_sample_Person_Companion thiz);
                } Companion;
                libkn_KType* (*_type)(void);
                libkn_kref_com_tencent_compose_sample_Person (*Person)(const char* name, libkn_KInt age);
                libkn_KInt (*get_age)(libkn_kref_com_tencent_compose_sample_Person thiz);
                const char* (*get_name)(libkn_kref_com_tencent_compose_sample_Person thiz);
                const char* (*component1)(libkn_kref_com_tencent_compose_sample_Person thiz);
                libkn_KInt (*component2)(libkn_kref_com_tencent_compose_sample_Person thiz);
                libkn_kref_com_tencent_compose_sample_Person (*copy)(libkn_kref_com_tencent_compose_sample_Person thiz, const char* name, libkn_KInt age);
                libkn_KBoolean (*equals)(libkn_kref_com_tencent_compose_sample_Person thiz, libkn_kref_kotlin_Any other);
                libkn_KInt (*hashCode)(libkn_kref_com_tencent_compose_sample_Person thiz);
                const char* (*toString)(libkn_kref_com_tencent_compose_sample_Person thiz);
              } Person;
              struct {
                struct {
                  libkn_KType* (*_type)(void);
                  libkn_kref_com_tencent_compose_sample_XmlUtilTest_Companion (*_instance)();
                } Companion;
                libkn_KType* (*_type)(void);
                libkn_kref_com_tencent_compose_sample_XmlUtilTest (*XmlUtilTest)();
                libkn_kref_com_tencent_compose_sample_Person (*testXmlDeserialization)(libkn_kref_com_tencent_compose_sample_XmlUtilTest thiz, const char* xml);
                const char* (*testXmlSerialization)(libkn_kref_com_tencent_compose_sample_XmlUtilTest thiz);
              } XmlUtilTest;
              libkn_KInt (*com_tencent_compose_sample_DatabaseHelper$stableprop_getter)();
              libkn_KInt (*com_tencent_compose_sample_KsoupTest$stableprop_getter)();
              libkn_KInt (*com_tencent_compose_sample_Person$stableprop_getter)();
              libkn_KInt (*com_tencent_compose_sample_Person_$serializer$stableprop_getter)();
              libkn_KInt (*com_tencent_compose_sample_XmlUtilTest$stableprop_getter)();
              libkn_KInt (*com_tencent_compose_sample_DatabaseHelper$stableprop_getter_)();
              libkn_KInt (*com_tencent_compose_sample_KsoupTest$stableprop_getter_)();
              libkn_KInt (*com_tencent_compose_sample_Person$stableprop_getter_)();
              libkn_KInt (*com_tencent_compose_sample_Person_$serializer$stableprop_getter_)();
              libkn_KInt (*com_tencent_compose_sample_XmlUtilTest$stableprop_getter_)();
              libkn_KInt (*com_tencent_compose_sample_DatabaseHelper$stableprop_getter__)();
              libkn_KInt (*com_tencent_compose_sample_KsoupTest$stableprop_getter__)();
              libkn_KInt (*com_tencent_compose_sample_Person$stableprop_getter__)();
              libkn_KInt (*com_tencent_compose_sample_Person_$serializer$stableprop_getter__)();
              libkn_KInt (*com_tencent_compose_sample_XmlUtilTest$stableprop_getter__)();
              libkn_KInt (*com_tencent_compose_sample_DatabaseHelper$stableprop_getter___)();
              libkn_KInt (*com_tencent_compose_sample_KsoupTest$stableprop_getter___)();
              libkn_KInt (*com_tencent_compose_sample_Person$stableprop_getter___)();
              libkn_KInt (*com_tencent_compose_sample_Person_$serializer$stableprop_getter___)();
              libkn_KInt (*com_tencent_compose_sample_XmlUtilTest$stableprop_getter___)();
              libkn_KInt (*com_tencent_compose_sample_DatabaseHelper$stableprop_getter____)();
              libkn_KInt (*com_tencent_compose_sample_KsoupTest$stableprop_getter____)();
              libkn_KInt (*com_tencent_compose_sample_Person$stableprop_getter____)();
              libkn_KInt (*com_tencent_compose_sample_Person_$serializer$stableprop_getter____)();
              libkn_KInt (*com_tencent_compose_sample_XmlUtilTest$stableprop_getter____)();
              libkn_KInt (*com_tencent_compose_sample_DatabaseHelper$stableprop_getter_____)();
              libkn_KInt (*com_tencent_compose_sample_KsoupTest$stableprop_getter_____)();
              libkn_KInt (*com_tencent_compose_sample_Person$stableprop_getter_____)();
              libkn_KInt (*com_tencent_compose_sample_Person_$serializer$stableprop_getter_____)();
              libkn_KInt (*com_tencent_compose_sample_XmlUtilTest$stableprop_getter_____)();
              libkn_KInt (*com_tencent_compose_sample_DatabaseHelper$stableprop_getter______)();
              libkn_KInt (*com_tencent_compose_sample_KsoupTest$stableprop_getter______)();
              libkn_KInt (*com_tencent_compose_sample_Person$stableprop_getter______)();
              libkn_KInt (*com_tencent_compose_sample_Person_$serializer$stableprop_getter______)();
              libkn_KInt (*com_tencent_compose_sample_XmlUtilTest$stableprop_getter______)();
              libkn_KInt (*com_tencent_compose_sample_DatabaseHelper$stableprop_getter_______)();
              libkn_KInt (*com_tencent_compose_sample_KsoupTest$stableprop_getter_______)();
              libkn_KInt (*com_tencent_compose_sample_Person$stableprop_getter_______)();
              libkn_KInt (*com_tencent_compose_sample_Person_$serializer$stableprop_getter_______)();
              libkn_KInt (*com_tencent_compose_sample_XmlUtilTest$stableprop_getter_______)();
              void* (*get_nativeResourceManager)();
              void (*set_nativeResourceManager)(void* set);
              libkn_KInt (*com_tencent_compose_sample_DatabaseHelper$stableprop_getter________)();
              libkn_KInt (*com_tencent_compose_sample_KsoupTest$stableprop_getter________)();
              libkn_KInt (*com_tencent_compose_sample_Person$stableprop_getter________)();
              libkn_KInt (*com_tencent_compose_sample_Person_$serializer$stableprop_getter________)();
              libkn_KInt (*com_tencent_compose_sample_XmlUtilTest$stableprop_getter________)();
              libkn_KInt (*com_tencent_compose_sample_DatabaseHelper$stableprop_getter_________)();
              libkn_KInt (*com_tencent_compose_sample_KsoupTest$stableprop_getter_________)();
              libkn_KInt (*com_tencent_compose_sample_Person$stableprop_getter_________)();
              libkn_KInt (*com_tencent_compose_sample_Person_$serializer$stableprop_getter_________)();
              libkn_KInt (*com_tencent_compose_sample_XmlUtilTest$stableprop_getter_________)();
              libkn_KInt (*com_tencent_compose_sample_DatabaseHelper$stableprop_getter__________)();
              libkn_KInt (*com_tencent_compose_sample_KsoupTest$stableprop_getter__________)();
              libkn_KInt (*com_tencent_compose_sample_Person$stableprop_getter__________)();
              libkn_KInt (*com_tencent_compose_sample_Person_$serializer$stableprop_getter__________)();
              libkn_KInt (*com_tencent_compose_sample_XmlUtilTest$stableprop_getter__________)();
              libkn_KInt (*com_tencent_compose_sample_DatabaseHelper$stableprop_getter___________)();
              libkn_KInt (*com_tencent_compose_sample_KsoupTest$stableprop_getter___________)();
              libkn_KInt (*com_tencent_compose_sample_Person$stableprop_getter___________)();
              libkn_KInt (*com_tencent_compose_sample_Person_$serializer$stableprop_getter___________)();
              libkn_KInt (*com_tencent_compose_sample_XmlUtilTest$stableprop_getter___________)();
              libkn_KInt (*com_tencent_compose_sample_DatabaseHelper$stableprop_getter____________)();
              libkn_KInt (*com_tencent_compose_sample_KsoupTest$stableprop_getter____________)();
              libkn_KInt (*com_tencent_compose_sample_Person$stableprop_getter____________)();
              libkn_KInt (*com_tencent_compose_sample_Person_$serializer$stableprop_getter____________)();
              libkn_KInt (*com_tencent_compose_sample_XmlUtilTest$stableprop_getter____________)();
              libkn_KInt (*com_tencent_compose_sample_DatabaseHelper$stableprop_getter_____________)();
              libkn_KInt (*com_tencent_compose_sample_KsoupTest$stableprop_getter_____________)();
              libkn_KInt (*com_tencent_compose_sample_Person$stableprop_getter_____________)();
              libkn_KInt (*com_tencent_compose_sample_Person_$serializer$stableprop_getter_____________)();
              libkn_KInt (*com_tencent_compose_sample_XmlUtilTest$stableprop_getter_____________)();
              void (*deletesql)(libkn_KLong id, libkn_kref_kotlin_Function2 callback);
              void (*insertsql)(const char* name, libkn_KInt age, const char* email, libkn_kref_kotlin_Function2 callback);
              void (*processResultSet)(libkn_kref_kotlin_Any resultSet, libkn_kref_kotlin_Function1 callback);
              void (*querysql)(libkn_kref_kotlin_Function2 callback);
              void (*updatesql)(libkn_KLong id, const char* name, libkn_KInt age, const char* email, libkn_kref_kotlin_Function2 callback);
            } sample;
            libkn_KInt (*com_tencent_compose_Greeting$stableprop_getter)();
            libkn_KInt (*com_tencent_compose_OHOSPlatform$stableprop_getter)();
            libkn_KInt (*com_tencent_compose_Greeting$stableprop_getter_)();
            libkn_KInt (*com_tencent_compose_OHOSPlatform$stableprop_getter_)();
            libkn_KInt (*com_tencent_compose_Greeting$stableprop_getter__)();
            libkn_KInt (*com_tencent_compose_OHOSPlatform$stableprop_getter__)();
            void* (*MainArkUIViewController_)(void* env);
            libkn_KInt (*com_tencent_compose_Greeting$stableprop_getter___)();
            libkn_KInt (*com_tencent_compose_OHOSPlatform$stableprop_getter___)();
            void (*initResourceManager)(void* resourceManager);
            libkn_KInt (*com_tencent_compose_Greeting$stableprop_getter____)();
            libkn_KInt (*com_tencent_compose_OHOSPlatform$stableprop_getter____)();
          } compose;
        } tencent;
      } com;
      struct {
        struct {
          struct {
            struct {
              libkn_KInt (*composesample_composeapp_generated_resources_Res$stableprop_getter)();
              libkn_KInt (*composesample_composeapp_generated_resources_Res_drawable$stableprop_getter)();
              libkn_KInt (*composesample_composeapp_generated_resources_Res_font$stableprop_getter)();
              libkn_KInt (*composesample_composeapp_generated_resources_Res_string$stableprop_getter)();
              libkn_KInt (*composesample_composeapp_generated_resources_Res$stableprop_getter_)();
              libkn_KInt (*composesample_composeapp_generated_resources_Res_drawable$stableprop_getter_)();
              libkn_KInt (*composesample_composeapp_generated_resources_Res_font$stableprop_getter_)();
              libkn_KInt (*composesample_composeapp_generated_resources_Res_string$stableprop_getter_)();
            } resources;
          } generated;
        } composeapp;
      } composesample;
    } root;
  } kotlin;
} libkn_ExportedSymbols;
extern libkn_ExportedSymbols* libkn_symbols(void);
#ifdef __cplusplus
}  /* extern "C" */
#endif
#endif  /* KONAN_LIBKN_H */
