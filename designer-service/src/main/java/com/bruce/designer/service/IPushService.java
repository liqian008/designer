package com.bruce.designer.service;

public interface IPushService {

	/* push初始化 */
	public void init();

	/**
	 * push消息
	 * 
	 * @param messageType
	 *            消息类型
	 * @param content
	 *            消息内容
	 * @param sourceId
	 *            源id
	 * @param toIdSet
	 * @return
	 */
	// public int pushMessage(int messageType, String content, long sourceId,
	// int fromId, Set<Integer> toIdSet);

	/**
	 * push消息
	 * 
	 * @param messageType
	 *            消息类型
	 * @param content
	 *            消息内容
	 * @param sourceId
	 *            源id
	 * @param toId
	 * @return
	 */
	public int pushMessage(int messageType, String content, long sourceId,
			int fromId, int toId);

	
	
	public static class PushAndroidMessage {

		private String title;
		private String description;
		private int open_type;
		private int notification_builder_id;
		private int notification_basic_style;
		private String custom_content;
		
		
		public PushAndroidMessage(String title, String description,
				int open_type, int notification_builder_id,
				int notification_basic_style, String custom_content) {
			super();
			this.title = title;
			this.description = description;
			this.open_type = open_type;
			this.notification_builder_id = notification_builder_id;
			this.notification_basic_style = notification_basic_style;
			this.custom_content = custom_content;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public int getOpen_type() {
			return open_type;
		}

		public void setOpen_type(int open_type) {
			this.open_type = open_type;
		}

		public int getNotification_builder_id() {
			return notification_builder_id;
		}

		public void setNotification_builder_id(int notification_builder_id) {
			this.notification_builder_id = notification_builder_id;
		}

		public int getNotification_basic_style() {
			return notification_basic_style;
		}

		public void setNotification_basic_style(int notification_basic_style) {
			this.notification_basic_style = notification_basic_style;
		}

		public String getCustom_content() {
			return custom_content;
		}

		public void setCustom_content(String custom_content) {
			this.custom_content = custom_content;
		}

	}

}
