package nkucs1416.simpbook.util;

public class Class1 {
    private int id;
    private String name;
    private int color;


    /**
     * 构建一个Class1实例
     *
     * @param tId id
     * @param tName 名称
     * @param tColor 标识颜色
     */
    public Class1(int tId, String tName, int tColor) {
        id = tId;
        name = tName;
        color = tColor;
    }


    /**
     * 获取Id
     *
     * @return Id
     */
    public int getId() {
        return id;
    }

    /**
     * 获取名称
     *
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取标识颜色
     *
     * @return 标识颜色
     */
    public int getColor() {
        return color;
    }

}
