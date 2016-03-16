package com.vo;

import com.util.FieldInfo;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Administrator on 2016/3/16 0016.
 */
public class TestFieldInfo {

    @Test
    public void test() {
        RequestMsg requestMsg = new RequestMsg();
        requestMsg.setId("1");
        requestMsg.setName("qing");
        requestMsg.setAddress("chang");
        String msg = null;
        try {
            msg = getMsgByFieldInfo(requestMsg);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(msg);

    }

    public String getMsgByFieldInfo(Object o) throws IllegalAccessException {
        List<Field> fields = Arrays.asList(o.getClass().getDeclaredFields());
        Collections.sort(fields, new Comparator<Field>() {
            public int compare(Field o1, Field o2) {
                FieldInfo fieldInfo1 = o1.getAnnotation(FieldInfo.class);
                int index1 = 0;
                if (null != fieldInfo1) {
                    index1 = Integer.parseInt(fieldInfo1.index());
                }
                FieldInfo fieldInfo2 = o1.getAnnotation(FieldInfo.class);
                int index2 = 0;
                if (null != fieldInfo2) {
                    index2 = Integer.parseInt(fieldInfo2.index());
                }
                return index1 - index2;
            }
        });
        StringBuilder msg = new StringBuilder();
        for (Field field : fields) {
            FieldInfo fieldInfo = field.getAnnotation(FieldInfo.class);
            if (null != fieldInfo) {
                if (FieldInfo.ALIGN.left.equals(fieldInfo.align())) {
                    if (!field.isAccessible()) field.setAccessible(true);
                    msg.append(StringUtils.rightPad(String.valueOf(field.get(o)), fieldInfo.length(), fieldInfo.pad()));
                } else if (FieldInfo.ALIGN.right.equals(fieldInfo.align())) {
                    if (!field.isAccessible()) field.setAccessible(true);
                    msg.append(StringUtils.leftPad(String.valueOf(field.get(o)), fieldInfo.length(), fieldInfo.pad()));

                }
            }
        }
        return msg.toString();
    }
}
