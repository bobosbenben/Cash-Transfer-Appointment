package en.common.util.helper;

import org.springframework.util.StringUtils;

public final class HibernateCondition {
    /**
     * @param hqlCondition
     * @return
     */
    public static String getRealCondition(String hqlCondition) {
        if (hqlCondition == null || hqlCondition.trim().length() == 0)
            return "";

        // 注意先后顺序
        hqlCondition = StringUtils.replace(hqlCondition, "&(", "and (o.");
        hqlCondition = StringUtils.replace(hqlCondition, "&", "and o.");
        hqlCondition = StringUtils.replace(hqlCondition, "|(", "or (o.");
        hqlCondition = StringUtils.replace(hqlCondition, "|", "or o.");
        return hqlCondition;
    }
    public static String getRealSql(String hqlCondition) {
        if (hqlCondition == null || hqlCondition.trim().length() == 0)
            return "";

        // 注意先后顺序
        hqlCondition = StringUtils.replace(hqlCondition, "&(", "and ( ");
        hqlCondition = StringUtils.replace(hqlCondition, "&", "and ");
        hqlCondition = StringUtils.replace(hqlCondition, "|(", "or (");
        hqlCondition = StringUtils.replace(hqlCondition, "|", "or ");
        return hqlCondition;
    }
}