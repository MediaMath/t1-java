package com.mediamath.terminalone.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.*;
import com.mediamath.terminalone.exceptions.ClientException;
import com.mediamath.terminalone.utils.Utility;
import org.javers.core.metamodel.annotation.DiffIgnore;

import javax.ws.rs.core.Form;
import java.lang.reflect.Type;
import java.util.List;

public class DmpSegment implements T1Entity {

	public enum SegmentType {
		adaptive("adaptive");
		String value;

		SegmentType(String s) {
			value = s;
		}
		public String getValue(){
			return value;
		}
	}

	public enum OwnerType {
		advertiser("advertiser");
		String value;

		OwnerType(String s) {
			value = s;
		}
		public String getValue(){
			return value;
		}
	}

	public enum SegmentStatus {
		enabled("enabled");
		String value;

		SegmentStatus(String s) {
			value = s;
		}
		public String getValue(){
			return value;
		}
	}

/*	"expression": {
		"op": "and",
		"values": [
		{
			"op": "or",
			"values": [
					"id_319163",
					"id_319164"
                    ]
		},
		{
			"op": "or",
			"values": [
					"id_319165",
					{
						"op": "and",
						"values": [
							"id_319166",
							{
								"op": "not",
								"expression": "id_319167"
                            }
                            ]
                     }
                    ]
		}
       ]
	}  */
	public static class Expression {
		public enum Op {
			and("and"), or("or"), not("not");
			String value;

			Op(String s) {
				value = s;
			}
			public String getValue(){
				return value;
			}
		}

		private Op op;
		private List<Expression> values;
		private String expression;

		public Op getOp() {
			return op;
		}

		public void setOp(Op op) {
			this.op = op;
		}

		public List<Expression> getValues() {
			return values;
		}

		public void setValues(List<Expression> values) {
			this.values = values;
		}

		public String getExpression() {
			return expression;
		}

		public void setExpression(String expression) {
			this.expression = expression;
		}
	}

	public static class ExpressionSerializer implements JsonSerializer<Expression> {
		@Override
		public JsonElement serialize(DmpSegment.Expression expression, Type paramType, JsonSerializationContext context) throws JsonParseException {
			//{"op":"and","values":[{"op":"or","values":["id_319163","id_319164"]},{"op":"or","values":["id_319165",{"op":"and","values":["id_319166",{"op":"not","expression":"id_319167"}]}]}]}
			if (expression == null) {
				return JsonNull.INSTANCE;
			}

			if (expression.getOp() == null) {
				return new JsonPrimitive(expression.getExpression());
			}

			JsonObject obj = new JsonObject();
			obj.addProperty("op", expression.getOp().getValue());

			if (expression.getValues() == null || expression.getValues().isEmpty()) {
				obj.addProperty("expression", expression.getExpression());
				return obj;
			}

			JsonArray elems = new JsonArray();
			for(DmpSegment.Expression value : expression.getValues()) {
				elems.add(serialize(value, null, null));
			}
			obj.add("values", elems);
			return obj;
		}
	}

	private static final String entityName = "DmpSegment";

	private int id;
	private int pixel_id;
	private String name;
	private SegmentType segment_type;
	private int version;
	private Expression expression;
	private int owner_id;
	private OwnerType owner_type;
	private SegmentStatus status;
	private boolean editable;

	public static String getEntityName() {
		return entityName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPixelId() {
		return pixel_id;
	}

	public void setPixelId(int pixel_id) {
		this.pixel_id = pixel_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	public int getOwnerId() {
		return owner_id;
	}

	public void setOwnerId(int owner_id) {
		this.owner_id = owner_id;
	}

	public SegmentType getSegmentType() {
		return segment_type;
	}

	public void setSegmentType(SegmentType segment_type) {
		this.segment_type = segment_type;
	}

	public OwnerType getOwnerType() {
		return owner_type;
	}

	public void setOwnerType(OwnerType owner_type) {
		this.owner_type = owner_type;
	}

	public SegmentStatus getStatus() {
		return status;
	}

	public void setStatus(SegmentStatus status) {
		this.status = status;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Override
	public String getEntityname() {
		return entityName;
	}

	@Override
	@DiffIgnore
	@JsonIgnore
	public Form getForm() {
		Form dmpSegmentForm = new Form();

		if(this.getName() != null){
			dmpSegmentForm.param("name", this.getName());
		}

		if(this.getId() > 0){
			dmpSegmentForm.param("id", String.valueOf(getId()));
		}

		if(this.getPixelId() > 0){
			dmpSegmentForm.param("pixel_id", String.valueOf(getPixelId()));
		}

		if(this.getSegmentType() != null){
			dmpSegmentForm.param("segment_type", getSegmentType().getValue());
		}

		if(this.getOwnerType() != null){
			dmpSegmentForm.param("owner_type", getOwnerType().getValue());
		}

		if(this.getStatus() != null){
			dmpSegmentForm.param("status", getStatus().getValue());
		}

		if (this.getVersion() > 0) {
			dmpSegmentForm.param("version", String.valueOf(this.getVersion()));
		}

		if(this.getOwnerId() > 0){
			dmpSegmentForm.param("owner_id", String.valueOf(getOwnerId()));
		}

		if(this.getExpression() != null) {
			JsonElement expr = new ExpressionSerializer().serialize(getExpression(),null,null);
			JsonObject wrapper = new JsonObject();
			wrapper.add("expression", expr);
			Gson gson = new GsonBuilder().create();
			dmpSegmentForm.param("expression", gson.toJson(wrapper));
		}

		dmpSegmentForm.param("editable", String.valueOf(isEditable()));

		dmpSegmentForm = Utility.getFilteredForm(dmpSegmentForm, entityName);

		return dmpSegmentForm;
	}

	@Override
	@DiffIgnore
	@JsonIgnore
	public String getUri() throws ClientException {
		StringBuilder uri = new StringBuilder();

		if (this.getId() > 0) {
			uri.append("/");
			uri.append(this.getId());
		}

		return uri.toString();
	}

}
