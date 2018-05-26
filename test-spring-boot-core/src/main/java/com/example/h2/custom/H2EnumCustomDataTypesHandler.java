package com.example.h2.custom;

import org.h2.api.CustomDataTypesHandler;
import org.h2.store.DataHandler;
import org.h2.value.DataType;
import org.h2.value.Value;

/**
 * H2のドライバがH2のENUMの変換に対応していないので、EnumをStringとして扱う
 * CustomDataTypesHandlerを用意する
 * ※
 * org.h2.value.DataType.getTypeClassName(int)の中にENUM用のcase文がない
 * JVMに -Dh2.customDataTypesHandler=package名.H2EnumCustomDataTypesHandler 渡す必要あり
 *
 */
public class H2EnumCustomDataTypesHandler implements CustomDataTypesHandler {

	@Override
	public DataType getDataTypeByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataType getDataTypeById(int type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDataTypeOrder(int type) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Value convert(Value source, int targetType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDataTypeClassName(int type) {
		// TODO Auto-generated method stub
		return String.class.getName();
	}

	@Override
	public int getTypeIdFromClass(Class<?> cls) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Value getValue(int type, Object data, DataHandler dataHandler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getObject(Value value, Class<?> cls) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supportsAdd(int type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getAddProofType(int type) {
		// TODO Auto-generated method stub
		return 0;
	}

}
