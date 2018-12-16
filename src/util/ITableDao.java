package util;
import java.util.*;
/**
 * @version 时间：2018年5月12日 上午9:07:35
 *接口
 */
public interface ITableDao {
	//查所有
	List<HashMap> getAll();
	//查单个
	HashMap<String,Object> getOne(String sql,Object... params);
	//新增
	int insert (String sql,Object... params);
	//修改
	int update(String sql,Object... params);
	//删除
	int delete(String sql,Object... params);
}
