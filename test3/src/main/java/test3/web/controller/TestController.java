package test3.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import test3.TestBean;
import test3.TestEntity;
import test3.TestEnumStatus;
import test3.TestSession;
import test3.app.service.TestService;
import test3.web.form.TestForm;
import test3.web.view.EnumViewName;

// Session�̊m�F��TestController2�ŏo����

// �C���[�W�I�ɂ�@ModelAttribute()�Ŏw�肵�Ă镶����(HTML��form��name)���L�[�l�ɂ��āA
// request(@SessionAttributes()�Ŏw�肳��Ă�΃Z�b�V�����ɂ�)��addAttribute���Ă�悤�Ȋ����Ȃ̂��ȁB�B

// ModelAttritubte�Ŏw�肳�ꂽ�I�u�W�F�N�g�� new �Ő�������Ă���̂ŁA�V���O���g���ɂ͂Ȃ�Ȃ�

@Controller
@SessionAttributes(types = { TestSession.class, TestForm.class })
public class TestController extends WebApplicationObjectSupport {

	private TestService testService;

	@Autowired
	private TestBean testBean;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value(value = "${hoge.test}")
	private String hoge;

	@Resource
	private MessageSource messageSource;

	@Value(value = "${catalina.home}")
	private String moge;

	@Value(value = "${test.hogehoge}")
	private String testHogehoge;

	@Value(value = "${test2.hogehoge}")
	private String test2Hogehoge;

	// @ModelAttribute��value�Ɏw�肵���l���������Areturn�����̂ɂȂ��āAmodel��add�����
	@ModelAttribute("modelAttributeTest1")
	public String modelAttributeTest1() {

		System.out.println("modelAttributeTest1");
		return "modelAttributeTest1";
	}

	// @ModelAttribute��value���w�肵�Ȃ��ꍇ�A��������string�ɂȂ�
	@ModelAttribute
	public String modelAttributeTest2() {

		System.out.println("modelAttributeTest2");
		return "modelAttributeTest2";
	}

	@ModelAttribute("testForm")
	public TestForm testForm() {

		System.out.println("testForm");
		return new TestForm();
	}

	@RequestMapping(value = "/Test")
	public String test(Locale locale, Model model) {
		System.out.println("TEST1");
		System.out.println("TEST2");

		// Get model attribute -<
		String modelAttributeTest1 = (String) model.asMap().get("modelAttributeTest1");
		// Get model attribute ->

		// Test spring DI getBean <-
		ApplicationContext context = getApplicationContext();
		InternalResourceViewResolver rsl = (InternalResourceViewResolver) context
				.getBean("org.springframework.web.servlet.view.InternalResourceViewResolver");
				// Test spring DI getBean ->

		// Test spring jdbcTemplete <-
		RowMapper<TestEntity> mapper = new BeanPropertyRowMapper<TestEntity>(TestEntity.class);
		List<TestEntity> dtoList = jdbcTemplate.query("select * from test", mapper);
		// Test spring jdbcTemplete ->

		transactional();

		// Test spring message <-
		String message = messageSource.getMessage("hoge.message1", null, locale);
		// Test spring message ->

		// Test spring model <-
		model.addAttribute("modelMessage1", "model.addAttribute()");
		// Test spring model ->

		// Test StreamAPI <-
		List<String> hoge = new ArrayList<String>();
		hoge.add("test01");
		hoge.add("test02");
		hoge.add("test03");

		hoge.forEach(aaa -> System.out.println(aaa));
		hoge.stream().filter(x -> x.equals("test01")).forEach(System.out::println);

		Arrays.stream(new String[] { "test11", "test12", "test13" }).forEach(System.out::println);
		// Test StreamAPI ->

		// Test ramda <-
		testBean.ramdaTest(x -> {
			System.out.println(x);
		});

		testBean.ramdaTestMyfunc(x -> {
			System.out.println(x);
		});
		// Test ramda ->

		// model��Thymeleaf��foreach�e�X�g�p�̃f�[�^�ǉ� -<
		model.addAttribute("listTest",
				Arrays.asList(new String[] { "list_test1", "list_test2", "list_test3", "list_test4" }));
		// model��Thymeleaf��foreach�e�X�g�p�̃f�[�^�ǉ� ->

		testService = new TestService();
		testService.service();

		// �^�C�v�Z�[�tEnum�i���܂��܌^�������������S�R�֌W�Ȃ��l�Ƃ̔�r�A�������h���j -<
		String nextView = "";
		switch (testBean.getTestStatus()) {
		case TEST_STATUS1:
			// �񋓂ɑΉ������R�[�h�l���~�����ꍇ�́��݂����Ȋ���
			System.out.println(TestEnumStatus.TEST_STATUS1.getCode());

			// �������ʂɉ���������ʗp��View�����擾����
			nextView = EnumViewName.TYMELEAF_SAMLE.getViewName();

			break;
		case TEST_STATUS2:
			// ��肠�����ȗ�
			break;

		case TEST_STATUS3:
			// ��肠�����ȗ�
			break;

		default:
			// ��肠�����ȗ�
			break;
		}
		// �^�C�v�Z�[�tEnum ->

		if (!model.containsAttribute("sessionTest")) {
			System.out.println("addSession");
			TestSession sess = new TestSession();
			sess.str1 = "TestSession.str1";
			model.addAttribute("sessionTest", sess);
		}
		return nextView;
	}

	@RequestMapping(value = "/TestVelocity")
	public String velocityTest(@ModelAttribute("testForm") TestForm form, Model model) {

		// VelocityView�̒��Łi#createVelocityContext�jmodel�̒l��VelocityContext��putAll���Ă���̂ŁA
		// model��addAttirbute����ƁA�e���v���[�g��Ŏg����
		model.addAttribute("listTest",
				Arrays.asList(new String[] { "list_test1", "list_test2", "list_test3", "list_test4" }));

		return EnumViewName.VELOCITY_SAMPLE.getViewName();
	}

	@RequestMapping(value = "/TestVelocityConfirm")
	public String velocityTestConfirm(@Validated @ModelAttribute("testForm") TestForm form, BindingResult result,
			Model model) {

		System.out.println("testForm=" + form);;

		// error_message.properties����R�[�h�l�����Ƀ��b�Z�[�W�����������Ă���
		result.getAllErrors().stream().forEach(System.out::println);
		FieldError fieldErr = null;

		//name1�̓R�[�h�l�𒼐ڎw��
		String errorName1 = messageSource.getMessage("NotEmpty.testForm.name1", null, Locale.getDefault());
		model.addAttribute("errorName1", errorName1);

		// name2�̓G���[�I�u�W�F�N�g����B�G���[���b�Z�[�W�̃R�[�h�l��spring�������Ő����������́B
		// �A�m�e�[�V�����Ŏw�肵���l��{1},{2}�Œu�������i�G���[�I�u�W�F�N�g��argments�Ɋi�[�����j
		if (((fieldErr = result.getFieldError("name2")) != null)) {
			String errorName2 = messageSource.getMessage(fieldErr, Locale.getDefault());
			model.addAttribute("errorName2", errorName2);
		}

		// name3�̓G���[�I�u�W�F�N�g����B�G���[���b�Z�[�W�̃R�[�h�l���A�m�e�[�V������message�Ŏw��B
		// ValidationMessages.properties����ǂݍ���
		if (((fieldErr = result.getFieldError("name3")) != null)) {
			String errorName3 = messageSource.getMessage(fieldErr, Locale.getDefault());
			model.addAttribute("errorName3", errorName3);
		}

		// name4�̓G���[�I�u�W�F�N�g����B�Ǝ��A�m�e�[�V�����Bfield�Ŏw�肵���l���v���[�X�z���_�[�ŕ\���B
		if (((fieldErr = result.getFieldError("name4")) != null)) {
			String errorName4 = messageSource.getMessage(result.getFieldError("name4"), Locale.getDefault());
			model.addAttribute("errorName4", errorName4);
		}

		return EnumViewName.VELOCITY_SAMPLE.getViewName();
	}

	public TestBean getTest() {
		return testBean;
	}

	@Autowired
	public void setTest(TestBean test) {
		this.testBean = test;
	}

	@Transactional
	public void transactional() {
		System.out.println("transactional()");
		RowMapper<TestEntity> mapper = new BeanPropertyRowMapper<TestEntity>(TestEntity.class);
		List<TestEntity> dtoList = jdbcTemplate.query("select * from test", mapper);
	}
}
