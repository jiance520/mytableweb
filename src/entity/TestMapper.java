package entity;
/**
 * @version ʱ�䣺2018��6��7�� ����7:54:34
 *
 */
public interface TestMapper {
	Test selectByPrimaryKey(Long l);
	int insert_seq(Test t);
}
