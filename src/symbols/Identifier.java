package symbols;

public class Identifier {
	private String text;
	private final DataType type;
	private Double  value;

	private String  valueText;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public DataType getType() {
		return type;
	}
	public Identifier(String text, DataType type) {
		super();
		this.text = text;
		this.type = type;
	}

	public boolean isAssigned() {
		return value != null || valueText != null;
	}
	@Override
	public String toString() {
		return "Identifier [text=" + text + ", type=" + type + ", value=" + value + "]";
	}

	public Double getValue() {
		return value;
	}

	public String getValueText() {
		return valueText;
	}

	public String show() {
		if (value != null) {
			return type == DataType.INTEGER ? String.valueOf((int) Math.round(value)) : String.valueOf(value);
		}
		return valueText;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public void setValueText(String valueText) {
		this.valueText = valueText;
	}
}
