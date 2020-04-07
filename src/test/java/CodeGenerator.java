import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Scanner;


/**
 * @Author Kaiyi Zhang
 * @Date 2019/04/01
 * @Description
 */

public class CodeGenerator {

	public static void main(String[] args) {
		entityGenerator();
	}

	public static void entityGenerator() {

		String[] tables = new String[]{
//				"user"
				"role"
		};

		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		gc.setOutputDir(projectPath + "/src/main/java");
		//gc.setOutputDir("/Users/blackbaka/WorkSpace/house-management/dao/src/main/java");//这里写你自己的java目录
		gc.setFileOverride(true);//是否覆盖
		gc.setActiveRecord(true);
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(true);// XML ResultMap
		gc.setBaseColumnList(false);// XML columList
		gc.setSwagger2(true);
		gc.setAuthor("yan hongbo");
		gc.setOpen(false);
		gc.setDateType(DateType.ONLY_DATE);
//		gc.setIdType(IdType.NONE);

		//配置全局配置
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDriverName("com.mysql.cj.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("123456");
		dsc.setUrl("jdbc:mysql://127.0.0.7:3306/shiro?characterEncoding=UTF-8&zeroDateTimeBehavior" +
				"=convertToNull&useSSL=true&verifyServerCertificate=false&&serverTimezone=UTC");
		//配置数据源配置
		mpg.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		//strategy.setTablePrefix(new String[]{"_"});// 此处可以修改为您的表前缀
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setEntityLombokModel(true);
		strategy.setEntityBuilderModel(true);
		strategy.setInclude(tables);
		mpg.setStrategy(strategy);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent(null);
		pc.setEntity("com.hobson.api.model");
		pc.setMapper("com.hobson.api.mapper");
		pc.setXml("com.hobson.api.mapping");
		String generateCaS = scanner("!!!是否覆盖当前的controller和service(是:1)");
		if ("1".equals(generateCaS)) {
			pc.setService("com.hobson.api.service");       //本项目没用，生成之后删掉
			pc.setServiceImpl("com.hobson.api.service.impl");   //本项目没用，生成之后删掉
			pc.setController("com.hobson.api.controller");
		} else {
			pc.setService("TTTT");       //本项目没用，生成之后删掉
			pc.setServiceImpl("TTTT");   //本项目没用，生成之后删掉
			pc.setController("TTTT");
		}
		mpg.setPackageInfo(pc);

		// 执行生成
		mpg.execute();

		System.out.println("mybatis-plus generator over.");

	}

	public static String scanner(String tip) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder help = new StringBuilder();
		help.append("请输入" + tip + "：");
		System.out.println(help.toString());
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (StringUtils.isNotEmpty(ipt)) {
				return ipt;
			}
		}
		throw new MybatisPlusException("请输入正确的" + tip + "！");
	}

}
