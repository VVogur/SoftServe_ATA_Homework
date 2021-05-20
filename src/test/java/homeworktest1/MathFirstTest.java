package homeworktest1;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MathFirstTest {

    MathFirst obj = new MathFirst();

    @BeforeMethod
    public void beforeMethodInput() {
        System.out.println("Test for Field \"Input\" was launched");
    }

    @AfterMethod
    public void afterMethodInput() {
        System.out.println("Test for Field \"Input\" was finished");
    }

    @Test
    public void test1Math(){
        Assert.assertEquals(obj.mathPlus(1, 2), 3);
    }
    @Test
    public void test2Math(){
        Assert.assertEquals(obj.mathPlus(-1, -2), -3);
    }
    @Test
    public void test3Math(){
        Assert.assertEquals(obj.mathMinus(-1, 5), -6);
    }
    @Test
    public void test4Math(){
        Assert.assertEquals(obj.mathMinus(-1, -2), 1);
    }
    @Test
    public void test5Math(){
        Assert.assertEquals(obj.mathMultiply(-1, 5), -5);
    }
    @Test
    public void test6Math(){
        Assert.assertEquals(obj.mathMultiply(2, 5), 10);
    }
    @Test
    public void test7Math(){
        Assert.assertEquals(obj.mathMultiply(2, 5), 10);
    }
    @Test
    public void test8Math(){ Assert.assertEquals(obj.mathSplit(6, 2), 3.0); }
    @Test
    public void test9Math(){ Assert.assertEquals(obj.mathSplit(2, 5), 0.4);
    }

    @Test
    public void test10Arr(){
        int x[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        int y[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        Assert.assertEquals(obj.compare(x, y), true);
    }

    @Test
    public void test11Arr(){
        int x[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        int y[] = {2, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        Assert.assertFalse(obj.compare(x, y));
    }

    @Test
    public void test12Arr(){
        int x[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        int y[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        Assert.assertTrue(obj.compare(x, y));
    }

    @Test
    public void test13str(){
        Assert.assertNull(obj.strN());
    }


}