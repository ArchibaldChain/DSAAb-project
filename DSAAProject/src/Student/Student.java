package Student;

/**
 * Notice: Encoding with UTF-8
 * <p>
 * Javabean
 * You can store the data in the format like ArrayList<Student>
 */

public class Student {
    // 属性包括：学号,姓名,性别,省,市,区县,高考成绩,南科大考试,毕业GPA,毕业去向,留学国家,留学大学,专业1,城市,大学,专业2,工作省份,工作城市,深造学位,工作单位,月薪
    private String id;
    private String name;
    private String sex;
    private String province;
    private String city;
    private String district;
    private float gaokao;
    private float sustech;
    private float GPA;
    private String dream; // 毕业去向
    private String abroadCountry;
    private String abroadUniversity;
    private String major1;
    private String domesticCity;
    private String domesticUniversity;
    private String major2;
    private String workProvince;
    private String workCity;
    private String degree;
    private String workPlace; // 工作单位
    private float salary;

    public Student(String[] items) {

        // Constructor
        id = items[0];
        name = items[1];
        sex = items[2];
        province = items[3];
        city = items[4];
        district = items[5];
        setGaokao(items[6]);// String to float
        setSustech(items[7]);
        setGPA(items[8]);
        dream = items[9];
        abroadCountry = items[10];
        abroadUniversity = items[11];
        major1 = items[12];
        domesticCity = items[13];
        domesticUniversity = items[14];
        major2 = items[15];
        workProvince = items[16];
        workCity = items[17];
        degree = items[18];
        workPlace = items[19];
        setSalary(items[20]);

        dataRegulator(items[10]);


    }

    @Override
    public String toString() {
        return "Student.Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", gaokao=" + gaokao +
                ", sustech=" + sustech +
                ", GPA=" + GPA +
                ", dream='" + dream + '\'' +
                ", abroadCountry='" + abroadCountry + '\'' +
                ", abroadUniversity='" + abroadUniversity + '\'' +
                ", major1='" + major1 + '\'' +
                ", domesticCity='" + domesticCity + '\'' +
                ", domesticUniversity='" + domesticUniversity + '\'' +
                ", major2='" + major2 + '\'' +
                ", workProvince='" + workProvince + '\'' +
                ", workCity='" + workCity + '\'' +
                ", degree='" + degree + '\'' +
                ", workPlace='" + workPlace + '\'' +
                ", salary=" + salary +
                '}';
    }

    /**
     * Make the HongKong   become "study aboard"
     */

    /**
     * Empty judge
     *
     * @return true/ false
     */
    static Boolean emptyJudger(String[] item) {
        return (item[3].equals("") && item[4].equals("") && item[9].equals(""));
    }

    /**
     * Set the dream, country, university
     * Remove the country name '国'
     *
     * @return
     */
    private void dataRegulator(String country) {
        if (this.abroadUniversity.contains("大学")) {
            this.abroadUniversity = this.abroadUniversity.substring(0, this.abroadUniversity.length() - 2);
        }
        if (this.domesticUniversity.contains("大学")) {
            this.domesticUniversity = this.domesticUniversity.substring(0, this.domesticUniversity.length() - 2);
        }

        if (this.dream.equals("国内读研")) {
            if (this.domesticUniversity.contains("香港") || this.domesticUniversity.contains("HK")) {
                this.dream = "出国深造";
                this.abroadCountry = "中国香港";
                this.abroadUniversity = this.getDomesticUniversity();
                this.major1 = this.major2;
                this.domesticUniversity = "";
                this.major2 = "";
            }
        }

        this.province = removeLastCharacter(this.province, "省");

        this.city = removeLastCharacter(this.city, "市");

        this.district = removeLastCharacter(this.district, "区");

        this.workProvince = removeLastCharacter(this.workProvince, "省");

        this.workCity = removeLastCharacter(this.workCity, "市");

        if (country.length() > 0 && country.charAt(country.length() - 1) == '国') {
            this.abroadCountry = country.substring(0, country.length() - 1);
        }

        if (this.abroadCountry.equals("中")) {
            if (this.abroadUniversity.contains("香港")) {
                this.abroadCountry = "中国香港";
            }
            if (this.abroadUniversity.contains("澳门")) {
                this.abroadCountry = "中国澳门";
            }

        }

        if (this.abroadCountry.equals("香港")){
            this.abroadCountry = "中国香港";
        }

        if (!this.workPlace.equals("") && !this.workPlace.equals("其他企业")  && !this.workPlace.equals("国企")
                && !this.workPlace.equals("自主创业") ){
            this.workPlace = "其他企业";
        }
    }

    private String removeLastCharacter(String pro, String words) {
        if (pro.contains(words))
            pro = pro.substring(0, pro.length() - 1);
        return pro;
    }
    // setters and getters...

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public float getGaokao() {
        return gaokao;
    }

    public void setGaokao(String gaokao) {
        this.gaokao = toFloat(gaokao);
    }

    public float getSustech() {
        return sustech;
    }

    public void setSustech(String sustech) {
        this.sustech = toFloat(sustech);
    }

    public float getGPA() {
        return GPA;
    }

    public void setGPA(String GPA) {
        this.GPA = toFloat(GPA);
    }

    public String getDream() {
        return dream;
    }

    public void setDream(String dream) {
        this.dream = dream;
    }

    public String getAbroadCountry() {
        return abroadCountry;
    }

    public void setAbroadCountry(String abroadCountry) {
        this.abroadCountry = abroadCountry;
    }

    public String getAbroadUniversity() {
        return abroadUniversity;
    }

    public void setAbroadUniversity(String abroadUniversity) {
        this.abroadUniversity = abroadUniversity;
    }

    public String getMajor1() {
        return major1;
    }

    public void setMajor1(String major1) {
        this.major1 = major1;
    }

    public String getDomesticCity() {
        return domesticCity;
    }

    public void setDomesticCity(String domesticCity) {
        this.domesticCity = domesticCity;
    }

    public String getDomesticUniversity() {
        return domesticUniversity;
    }

    public void setDomesticUniversity(String domesticUniversity) {
        this.domesticUniversity = domesticUniversity;
    }

    public String getMajor2() {
        return major2;
    }

    public void setMajor2(String major2) {
        this.major2 = major2;
    }

    public String getWorkProvince() {
        return workProvince;
    }

    public void setWorkProvince(String workProvince) {
        this.workProvince = workProvince;
    }

    public String getWorkCity() {
        return workCity;
    }

    public void setWorkCity(String workCity) {
        this.workCity = workCity;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(String salary) {

        this.salary = toFloat(salary);

    }

    /**
     * Format the string into float
     *
     * @param s
     * @return float
     */
    private float toFloat(String s) {
        float f = 0;
        try {
            f = Float.parseFloat(s); // The parameter may be "" or " " or something else and can't be parsed to float number.
        } catch (Exception e) {
            // ignore, f will be returned as 0
        }
        return f;
    }
}
