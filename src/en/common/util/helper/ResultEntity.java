package en.common.util.helper;

public class ResultEntity {

	private  int resultType;  //resultType=0表示错误
	
	private Object resultDesc;

	public int getResultType() {
		return resultType;
	}

	public void setResultType(int resultType) {
		this.resultType = resultType;
	}

	public Object getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(Object resultDesc) {
		this.resultDesc = resultDesc;
	}

	public boolean isError() {
        return resultType != 0;
    }
}
