// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: enum.proto

package boomegg.cn.wawa.proto;

public final class Enum {
  private Enum() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }
  /**
   * Protobuf enum {@code boomegg.cn.wawa.proto.ROOM_STATUS_CODE}
   */
  public enum ROOM_STATUS_CODE
      implements com.google.protobuf.Internal.EnumLite {
    /**
     * <code>ROOM_STATUS_IDLE = 0;</code>
     */
    ROOM_STATUS_IDLE(0),
    /**
     * <code>ROOM_STATUS_PLAYING = 1;</code>
     */
    ROOM_STATUS_PLAYING(1),
    /**
     * <code>ROOM_STATUS_TESTING = 2;</code>
     */
    ROOM_STATUS_TESTING(2),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>ROOM_STATUS_IDLE = 0;</code>
     */
    public static final int ROOM_STATUS_IDLE_VALUE = 0;
    /**
     * <code>ROOM_STATUS_PLAYING = 1;</code>
     */
    public static final int ROOM_STATUS_PLAYING_VALUE = 1;
    /**
     * <code>ROOM_STATUS_TESTING = 2;</code>
     */
    public static final int ROOM_STATUS_TESTING_VALUE = 2;


    public final int getNumber() {
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static ROOM_STATUS_CODE valueOf(int value) {
      return forNumber(value);
    }

    public static ROOM_STATUS_CODE forNumber(int value) {
      switch (value) {
        case 0: return ROOM_STATUS_IDLE;
        case 1: return ROOM_STATUS_PLAYING;
        case 2: return ROOM_STATUS_TESTING;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<ROOM_STATUS_CODE>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        ROOM_STATUS_CODE> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<ROOM_STATUS_CODE>() {
            public ROOM_STATUS_CODE findValueByNumber(int number) {
              return ROOM_STATUS_CODE.forNumber(number);
            }
          };

    private final int value;

    private ROOM_STATUS_CODE(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:boomegg.cn.wawa.proto.ROOM_STATUS_CODE)
  }

  /**
   * Protobuf enum {@code boomegg.cn.wawa.proto.GAME_RET_CODE}
   */
  public enum GAME_RET_CODE
      implements com.google.protobuf.Internal.EnumLite {
    /**
     * <code>RET_SUCC = 0;</code>
     */
    RET_SUCC(0),
    /**
     * <code>RET_NETWORK = 10;</code>
     */
    RET_NETWORK(10),
    /**
     * <code>RET_BAD_PACKAGE = 20;</code>
     */
    RET_BAD_PACKAGE(20),
    /**
     * <code>RET_WAWA_REG_FAIL_EXISTS = 100001;</code>
     */
    RET_WAWA_REG_FAIL_EXISTS(100001),
    /**
     * <code>RET_WAWA_REG_FAIL_NOT_EXISTS = 100002;</code>
     */
    RET_WAWA_REG_FAIL_NOT_EXISTS(100002),
    /**
     * <code>RET_WAWA_NOT_REG_YET = 100003;</code>
     */
    RET_WAWA_NOT_REG_YET(100003),
    /**
     * <code>RET_USER_LOGIN_NOT_SUPPORT_TYPE = 200001;</code>
     */
    RET_USER_LOGIN_NOT_SUPPORT_TYPE(200001),
    /**
     * <code>RET_USER_RELOGIN = 200002;</code>
     */
    RET_USER_RELOGIN(200002),
    /**
     * <code>RET_USER_FAIL_ROOM_NOT_EXISTS = 200003;</code>
     */
    RET_USER_FAIL_ROOM_NOT_EXISTS(200003),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>RET_SUCC = 0;</code>
     */
    public static final int RET_SUCC_VALUE = 0;
    /**
     * <code>RET_NETWORK = 10;</code>
     */
    public static final int RET_NETWORK_VALUE = 10;
    /**
     * <code>RET_BAD_PACKAGE = 20;</code>
     */
    public static final int RET_BAD_PACKAGE_VALUE = 20;
    /**
     * <code>RET_WAWA_REG_FAIL_EXISTS = 100001;</code>
     */
    public static final int RET_WAWA_REG_FAIL_EXISTS_VALUE = 100001;
    /**
     * <code>RET_WAWA_REG_FAIL_NOT_EXISTS = 100002;</code>
     */
    public static final int RET_WAWA_REG_FAIL_NOT_EXISTS_VALUE = 100002;
    /**
     * <code>RET_WAWA_NOT_REG_YET = 100003;</code>
     */
    public static final int RET_WAWA_NOT_REG_YET_VALUE = 100003;
    /**
     * <code>RET_USER_LOGIN_NOT_SUPPORT_TYPE = 200001;</code>
     */
    public static final int RET_USER_LOGIN_NOT_SUPPORT_TYPE_VALUE = 200001;
    /**
     * <code>RET_USER_RELOGIN = 200002;</code>
     */
    public static final int RET_USER_RELOGIN_VALUE = 200002;
    /**
     * <code>RET_USER_FAIL_ROOM_NOT_EXISTS = 200003;</code>
     */
    public static final int RET_USER_FAIL_ROOM_NOT_EXISTS_VALUE = 200003;


    public final int getNumber() {
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static GAME_RET_CODE valueOf(int value) {
      return forNumber(value);
    }

    public static GAME_RET_CODE forNumber(int value) {
      switch (value) {
        case 0: return RET_SUCC;
        case 10: return RET_NETWORK;
        case 20: return RET_BAD_PACKAGE;
        case 100001: return RET_WAWA_REG_FAIL_EXISTS;
        case 100002: return RET_WAWA_REG_FAIL_NOT_EXISTS;
        case 100003: return RET_WAWA_NOT_REG_YET;
        case 200001: return RET_USER_LOGIN_NOT_SUPPORT_TYPE;
        case 200002: return RET_USER_RELOGIN;
        case 200003: return RET_USER_FAIL_ROOM_NOT_EXISTS;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<GAME_RET_CODE>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        GAME_RET_CODE> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<GAME_RET_CODE>() {
            public GAME_RET_CODE findValueByNumber(int number) {
              return GAME_RET_CODE.forNumber(number);
            }
          };

    private final int value;

    private GAME_RET_CODE(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:boomegg.cn.wawa.proto.GAME_RET_CODE)
  }

  /**
   * Protobuf enum {@code boomegg.cn.wawa.proto.WAWA_NOTIFY_TYPE}
   */
  public enum WAWA_NOTIFY_TYPE
      implements com.google.protobuf.Internal.EnumLite {
    /**
     * <code>WAWA_NOTIFY_TYPE_INIT = 0;</code>
     */
    WAWA_NOTIFY_TYPE_INIT(0),
    /**
     * <code>WAWA_NOTIFY_TYPE_WIN = 1;</code>
     */
    WAWA_NOTIFY_TYPE_WIN(1),
    /**
     * <code>WAWA_NOTIFY_TYPE_LOSE = 2;</code>
     */
    WAWA_NOTIFY_TYPE_LOSE(2),
    /**
     * <code>WAWA_NOTIFY_TYPE_KICK = 3;</code>
     */
    WAWA_NOTIFY_TYPE_KICK(3),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>WAWA_NOTIFY_TYPE_INIT = 0;</code>
     */
    public static final int WAWA_NOTIFY_TYPE_INIT_VALUE = 0;
    /**
     * <code>WAWA_NOTIFY_TYPE_WIN = 1;</code>
     */
    public static final int WAWA_NOTIFY_TYPE_WIN_VALUE = 1;
    /**
     * <code>WAWA_NOTIFY_TYPE_LOSE = 2;</code>
     */
    public static final int WAWA_NOTIFY_TYPE_LOSE_VALUE = 2;
    /**
     * <code>WAWA_NOTIFY_TYPE_KICK = 3;</code>
     */
    public static final int WAWA_NOTIFY_TYPE_KICK_VALUE = 3;


    public final int getNumber() {
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static WAWA_NOTIFY_TYPE valueOf(int value) {
      return forNumber(value);
    }

    public static WAWA_NOTIFY_TYPE forNumber(int value) {
      switch (value) {
        case 0: return WAWA_NOTIFY_TYPE_INIT;
        case 1: return WAWA_NOTIFY_TYPE_WIN;
        case 2: return WAWA_NOTIFY_TYPE_LOSE;
        case 3: return WAWA_NOTIFY_TYPE_KICK;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<WAWA_NOTIFY_TYPE>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        WAWA_NOTIFY_TYPE> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<WAWA_NOTIFY_TYPE>() {
            public WAWA_NOTIFY_TYPE findValueByNumber(int number) {
              return WAWA_NOTIFY_TYPE.forNumber(number);
            }
          };

    private final int value;

    private WAWA_NOTIFY_TYPE(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:boomegg.cn.wawa.proto.WAWA_NOTIFY_TYPE)
  }

  /**
   * Protobuf enum {@code boomegg.cn.wawa.proto.USER_LOGIN_TYPE}
   */
  public enum USER_LOGIN_TYPE
      implements com.google.protobuf.Internal.EnumLite {
    /**
     * <code>USER_LOGIN_TYPE_INIT = 0;</code>
     */
    USER_LOGIN_TYPE_INIT(0),
    /**
     * <code>USER_LOGIN_TYPE_WX = 1;</code>
     */
    USER_LOGIN_TYPE_WX(1),
    /**
     * <code>USER_LOGIN_TYPE_SQ = 2;</code>
     */
    USER_LOGIN_TYPE_SQ(2),
    /**
     * <code>USER_LOGIN_TYPE_ALIPAY = 3;</code>
     */
    USER_LOGIN_TYPE_ALIPAY(3),
    /**
     * <code>USER_LOGIN_TYPE_VISIT = 4;</code>
     */
    USER_LOGIN_TYPE_VISIT(4),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>USER_LOGIN_TYPE_INIT = 0;</code>
     */
    public static final int USER_LOGIN_TYPE_INIT_VALUE = 0;
    /**
     * <code>USER_LOGIN_TYPE_WX = 1;</code>
     */
    public static final int USER_LOGIN_TYPE_WX_VALUE = 1;
    /**
     * <code>USER_LOGIN_TYPE_SQ = 2;</code>
     */
    public static final int USER_LOGIN_TYPE_SQ_VALUE = 2;
    /**
     * <code>USER_LOGIN_TYPE_ALIPAY = 3;</code>
     */
    public static final int USER_LOGIN_TYPE_ALIPAY_VALUE = 3;
    /**
     * <code>USER_LOGIN_TYPE_VISIT = 4;</code>
     */
    public static final int USER_LOGIN_TYPE_VISIT_VALUE = 4;


    public final int getNumber() {
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static USER_LOGIN_TYPE valueOf(int value) {
      return forNumber(value);
    }

    public static USER_LOGIN_TYPE forNumber(int value) {
      switch (value) {
        case 0: return USER_LOGIN_TYPE_INIT;
        case 1: return USER_LOGIN_TYPE_WX;
        case 2: return USER_LOGIN_TYPE_SQ;
        case 3: return USER_LOGIN_TYPE_ALIPAY;
        case 4: return USER_LOGIN_TYPE_VISIT;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<USER_LOGIN_TYPE>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        USER_LOGIN_TYPE> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<USER_LOGIN_TYPE>() {
            public USER_LOGIN_TYPE findValueByNumber(int number) {
              return USER_LOGIN_TYPE.forNumber(number);
            }
          };

    private final int value;

    private USER_LOGIN_TYPE(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:boomegg.cn.wawa.proto.USER_LOGIN_TYPE)
  }

  /**
   * Protobuf enum {@code boomegg.cn.wawa.proto.ROOM_GROUP_LIST_TYPE}
   */
  public enum ROOM_GROUP_LIST_TYPE
      implements com.google.protobuf.Internal.EnumLite {
    /**
     * <code>ROOM_GROUP_LIST_TYPE_DEFAULT = 0;</code>
     */
    ROOM_GROUP_LIST_TYPE_DEFAULT(0),
    /**
     * <code>ROOM_GROUP_LIST_TYPE_HOT = 1;</code>
     */
    ROOM_GROUP_LIST_TYPE_HOT(1),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>ROOM_GROUP_LIST_TYPE_DEFAULT = 0;</code>
     */
    public static final int ROOM_GROUP_LIST_TYPE_DEFAULT_VALUE = 0;
    /**
     * <code>ROOM_GROUP_LIST_TYPE_HOT = 1;</code>
     */
    public static final int ROOM_GROUP_LIST_TYPE_HOT_VALUE = 1;


    public final int getNumber() {
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static ROOM_GROUP_LIST_TYPE valueOf(int value) {
      return forNumber(value);
    }

    public static ROOM_GROUP_LIST_TYPE forNumber(int value) {
      switch (value) {
        case 0: return ROOM_GROUP_LIST_TYPE_DEFAULT;
        case 1: return ROOM_GROUP_LIST_TYPE_HOT;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<ROOM_GROUP_LIST_TYPE>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        ROOM_GROUP_LIST_TYPE> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<ROOM_GROUP_LIST_TYPE>() {
            public ROOM_GROUP_LIST_TYPE findValueByNumber(int number) {
              return ROOM_GROUP_LIST_TYPE.forNumber(number);
            }
          };

    private final int value;

    private ROOM_GROUP_LIST_TYPE(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:boomegg.cn.wawa.proto.ROOM_GROUP_LIST_TYPE)
  }

  /**
   * Protobuf enum {@code boomegg.cn.wawa.proto.USER_APPLY_TYPE}
   */
  public enum USER_APPLY_TYPE
      implements com.google.protobuf.Internal.EnumLite {
    /**
     * <code>APPLY_SUCC = 0;</code>
     */
    APPLY_SUCC(0),
    /**
     * <code>APPLY_FAIL_NO_MONEY = 1;</code>
     */
    APPLY_FAIL_NO_MONEY(1),
    /**
     * <code>APPLY_FAIL_CONN_BREAK = 2;</code>
     */
    APPLY_FAIL_CONN_BREAK(2),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>APPLY_SUCC = 0;</code>
     */
    public static final int APPLY_SUCC_VALUE = 0;
    /**
     * <code>APPLY_FAIL_NO_MONEY = 1;</code>
     */
    public static final int APPLY_FAIL_NO_MONEY_VALUE = 1;
    /**
     * <code>APPLY_FAIL_CONN_BREAK = 2;</code>
     */
    public static final int APPLY_FAIL_CONN_BREAK_VALUE = 2;


    public final int getNumber() {
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static USER_APPLY_TYPE valueOf(int value) {
      return forNumber(value);
    }

    public static USER_APPLY_TYPE forNumber(int value) {
      switch (value) {
        case 0: return APPLY_SUCC;
        case 1: return APPLY_FAIL_NO_MONEY;
        case 2: return APPLY_FAIL_CONN_BREAK;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<USER_APPLY_TYPE>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        USER_APPLY_TYPE> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<USER_APPLY_TYPE>() {
            public USER_APPLY_TYPE findValueByNumber(int number) {
              return USER_APPLY_TYPE.forNumber(number);
            }
          };

    private final int value;

    private USER_APPLY_TYPE(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:boomegg.cn.wawa.proto.USER_APPLY_TYPE)
  }

  /**
   * Protobuf enum {@code boomegg.cn.wawa.proto.NOTIFY_USER_READY_RESP_TYPE}
   */
  public enum NOTIFY_USER_READY_RESP_TYPE
      implements com.google.protobuf.Internal.EnumLite {
    /**
     * <code>USER_ACK = 0;</code>
     */
    USER_ACK(0),
    /**
     * <code>USER_LEAVE = 1;</code>
     */
    USER_LEAVE(1),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>USER_ACK = 0;</code>
     */
    public static final int USER_ACK_VALUE = 0;
    /**
     * <code>USER_LEAVE = 1;</code>
     */
    public static final int USER_LEAVE_VALUE = 1;


    public final int getNumber() {
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static NOTIFY_USER_READY_RESP_TYPE valueOf(int value) {
      return forNumber(value);
    }

    public static NOTIFY_USER_READY_RESP_TYPE forNumber(int value) {
      switch (value) {
        case 0: return USER_ACK;
        case 1: return USER_LEAVE;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<NOTIFY_USER_READY_RESP_TYPE>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        NOTIFY_USER_READY_RESP_TYPE> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<NOTIFY_USER_READY_RESP_TYPE>() {
            public NOTIFY_USER_READY_RESP_TYPE findValueByNumber(int number) {
              return NOTIFY_USER_READY_RESP_TYPE.forNumber(number);
            }
          };

    private final int value;

    private NOTIFY_USER_READY_RESP_TYPE(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:boomegg.cn.wawa.proto.NOTIFY_USER_READY_RESP_TYPE)
  }

  /**
   * Protobuf enum {@code boomegg.cn.wawa.proto.CONFIRM_USER_PLAY_RESP_TYPE}
   */
  public enum CONFIRM_USER_PLAY_RESP_TYPE
      implements com.google.protobuf.Internal.EnumLite {
    /**
     * <code>CONFIRM_SUCC = 0;</code>
     */
    CONFIRM_SUCC(0),
    /**
     * <code>CONFIRM_FAIL_NO_MONEY = 1;</code>
     */
    CONFIRM_FAIL_NO_MONEY(1),
    /**
     * <code>CONFIRM_FAIL_OTHER = 2;</code>
     */
    CONFIRM_FAIL_OTHER(2),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>CONFIRM_SUCC = 0;</code>
     */
    public static final int CONFIRM_SUCC_VALUE = 0;
    /**
     * <code>CONFIRM_FAIL_NO_MONEY = 1;</code>
     */
    public static final int CONFIRM_FAIL_NO_MONEY_VALUE = 1;
    /**
     * <code>CONFIRM_FAIL_OTHER = 2;</code>
     */
    public static final int CONFIRM_FAIL_OTHER_VALUE = 2;


    public final int getNumber() {
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static CONFIRM_USER_PLAY_RESP_TYPE valueOf(int value) {
      return forNumber(value);
    }

    public static CONFIRM_USER_PLAY_RESP_TYPE forNumber(int value) {
      switch (value) {
        case 0: return CONFIRM_SUCC;
        case 1: return CONFIRM_FAIL_NO_MONEY;
        case 2: return CONFIRM_FAIL_OTHER;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<CONFIRM_USER_PLAY_RESP_TYPE>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        CONFIRM_USER_PLAY_RESP_TYPE> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<CONFIRM_USER_PLAY_RESP_TYPE>() {
            public CONFIRM_USER_PLAY_RESP_TYPE findValueByNumber(int number) {
              return CONFIRM_USER_PLAY_RESP_TYPE.forNumber(number);
            }
          };

    private final int value;

    private CONFIRM_USER_PLAY_RESP_TYPE(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:boomegg.cn.wawa.proto.CONFIRM_USER_PLAY_RESP_TYPE)
  }


  static {
  }

  // @@protoc_insertion_point(outer_class_scope)
}
