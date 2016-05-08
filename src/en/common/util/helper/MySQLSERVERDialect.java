package en.common.util.helper;

import java.sql.Types;

import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.SQLServer2008Dialect;
import org.hibernate.type.StandardBasicTypes;

//public class MySQLSERVERDialect extends SQLServer2008Dialect{
public class MySQLSERVERDialect extends MySQLDialect {

	  public MySQLSERVERDialect() {  
	        super();
		  	registerColumnType(java.sql.Types.BOOLEAN, "bit" );
//	        registerHibernateType(Types.CHAR, StandardBasicTypes.STRING.getName());
//	        registerHibernateType(Types.NCHAR, StandardBasicTypes.STRING.getName());
//	        registerHibernateType(Types.NVARCHAR, StandardBasicTypes.STRING.getName());
//	        registerHibernateType(Types.LONGNVARCHAR, StandardBasicTypes.STRING.getName());
//	        registerHibernateType(Types.DECIMAL, StandardBasicTypes.DOUBLE.getName());
	    }
}
