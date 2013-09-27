package com.bruce.designer.constants;

public interface MessageEnum {

    public enum TypeEnum {
        /* 系统类型消息 */
        BROADCAST((short) 1),
        /* @类型消息 */
        AT((short) 2),
        /* 关注类型消息 */
        FLOWER((short) 3),
        /* 评论类型消息 */
        COMMENT((short) 4),
        /* like类型消息 */
        LIKE((short) 5),
        /* 收藏类型消息 */
        FAVORITIES((short) 6),
        /* 聊天类型消息 */
        CHAT((short) 7);

        short type;

        TypeEnum(short type) {
            this.type = type;
        }

        public short getType() {
            return type;
        }
    }

    public enum StatusEnum {
        /* 未读消息状态 */
        UNREAD((short) 0),
        /* 已读消息状态 */
        READ((short) 1);

        short status;

        StatusEnum(short status) {
            this.status = status;
        }

        public short getStatus() {
            return status;
        }
    }

    public enum SourceEnum {
        SYSTEM(0);

        int sourceId;

        SourceEnum(int sourceId) {
            this.sourceId = sourceId;
        }

        public int getSourceId() {
            return sourceId;
        }
    }

}
