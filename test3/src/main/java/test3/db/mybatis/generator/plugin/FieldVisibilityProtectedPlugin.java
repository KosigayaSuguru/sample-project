package test3.db.mybatis.generator.plugin;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class FieldVisibilityProtectedPlugin extends PluginAdapter {

	public FieldVisibilityProtectedPlugin() {
		super();
	}

	@Override
	public boolean validate(List<String> arg0) {
		return true;
	}

	@Override
	public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
			IntrospectedTable introspectedTable, ModelClassType modelClassType) {

		field.setVisibility(JavaVisibility.PROTECTED);
		return super.modelFieldGenerated(field, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
	}
}
