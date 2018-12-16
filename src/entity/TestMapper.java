package entity;
/**
 * @version 时间：2018年6月7日 下午7:54:34
 *
 */
public interface TestMapper {
	Test selectByPrimaryKey(Long l);
	int insert_seq(Test t);
}
