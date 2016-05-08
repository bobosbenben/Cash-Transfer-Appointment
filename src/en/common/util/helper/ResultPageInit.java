package en.common.util.helper;

import java.util.List;

public class ResultPageInit {
		/**
		 * 获取总记录数
		 * @return int
		 */
		private Long rowCount;

		/**
		 * 获取本页要显示的数据
		 * @return list
		 */
		private  List<?>  result;

		public Long getRowCount() {
			return rowCount;
		}

		public void setRowCount(Long rowCount) {
			this.rowCount = rowCount;
		}

		public List<?> getResult() {
			return result;
		}

		public void setResult(List<?> result) {
			this.result = result;
		}
}
