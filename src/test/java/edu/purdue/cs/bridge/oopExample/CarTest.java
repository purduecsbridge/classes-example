package edu.purdue.cs.bridge.oopExample;

import edu.purdue.cs.percolator.TestCase;
import edu.purdue.cs.percolator.util.DebugUtilities;
import edu.purdue.cs.percolator.util.SetupUtilities;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Purdue CS Bridge – Lab Example 2
 * CarTest.java
 * Test cases for the Car class.
 *
 * @version 07/26/2020
 */
@RunWith(Enclosed.class)
public class CarTest {

    private static Class<?> carClass;

    /**
     * Sets up the classes/methods for the test cases.
     */
    @BeforeClass
    public static void setup() {
        String packageName = SetupUtilities.getPackageToTest();

        try {
            carClass = Class.forName(packageName + "Car");
        } catch (ClassNotFoundException e) {
            Assert.fail("Make sure you've submitted the Car.java file.");
        }
    }

    /**
     * Tests for properties of the class.
     */
    public static class ClassTests {

        @Test
        @TestCase(name = "Car: Class is public", points = 1.5625)
        public void classIsPublic() {
            boolean isPublic = Modifier.isPublic(carClass.getModifiers());
            Assert.assertTrue("Make sure the Car class is declared public.", isPublic);
        }

        @Test
        @TestCase(name = "Car: Class is not static", points = 1.5625)
        public void classIsNotStatic() {
            boolean isStatic = Modifier.isStatic(carClass.getModifiers());
            Assert.assertFalse("Make sure the Car class is not declared static.", isStatic);
        }

        @Test
        @TestCase(name = "Car: Class is not abstract", points = 1.5625)
        public void classIsNotAbstract() {
            boolean isAbstract = Modifier.isAbstract(carClass.getModifiers());
            Assert.assertFalse("Make sure the Car class is not declared abstract.", isAbstract);
        }

        @Test
        @TestCase(name = "Car: Class is not final", points = 1.5625)
        public void classIsNotFinal() {
            boolean isFinal = Modifier.isFinal(carClass.getModifiers());
            Assert.assertFalse("Make sure the Car class is not declared final.", isFinal);
        }

    }

    /**
     * Tests for fields in the class.
     */
    public static class FieldTests {

        @Test
        @TestCase(name = "Car: `make` field declaration", points = 6.25)
        public void makeFieldDeclaration() {
            Field field = null;
            
            try {
                field = carClass.getDeclaredField("make");
            } catch (NoSuchFieldException e) {
                Assert.fail("Make sure you have declared the `make` field in the Car class.");
            }
            
            Assert.assertEquals("Make sure the `make` field is of type `String`.", String.class, field.getType());
            
            boolean isPrivate = Modifier.isPrivate(field.getModifiers());
            
            Assert.assertTrue("Make sure the `make` field is private.", isPrivate);
        }

        @Test
        @TestCase(name = "Car: `model` field declaration", points = 6.25)
        public void modelFieldDeclaration() {
            Field field = null;

            try {
                field = carClass.getDeclaredField("model");
            } catch (NoSuchFieldException e) {
                Assert.fail("Make sure you have declared the `model` field in the Car class.");
            }

            Assert.assertEquals("Make sure the `model` field is of type `String`.", String.class, field.getType());

            boolean isPrivate = Modifier.isPrivate(field.getModifiers());

            Assert.assertTrue("Make sure the `model` field is private.", isPrivate);
        }

        @Test
        @TestCase(name = "Car: `numCars` field declaration", points = 6.25)
        public void numCarsFieldDeclaration() {
            Field field = null;

            try {
                field = carClass.getDeclaredField("numCars");
            } catch (NoSuchFieldException e) {
                Assert.fail("Make sure you have declared the `numCars` field in the Car class.");
            }

            Assert.assertEquals("Make sure the `numCars` field is of type `int`.", int.class, field.getType());

            boolean isPrivate = Modifier.isPrivate(field.getModifiers());

            Assert.assertTrue("Make sure the `numCars` field is private.", isPrivate);

            boolean isStatic = Modifier.isStatic(field.getModifiers());

            Assert.assertTrue("Make sure the `numCars` field is static.", isStatic);
        }

    }

    /**
     * Tests for constructors in the class.
     */
    public static class ConstructorTests {

        @Test
        @TestCase(name = "Car: Constructor declaration", points = 6.25)
        public void constructorDeclaration() {
            Constructor<?> constructor = null;

            try {
                constructor = carClass.getDeclaredConstructor(String.class, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Make sure you have declared the constructor in the Car class with the correct parameters.");
            }

            boolean isPublic = Modifier.isPublic(constructor.getModifiers());

            Assert.assertTrue("Make sure the Car constructor is public.", isPublic);
        }

        @Test
        @TestCase(name = "Car: Constructor implementation", points = 6.25)
        public void constructorImplementation() {
            final String EXPECTED_MAKE = "Ford";
            final String EXPECTED_MODEL = "Focus ST";

            Field makeField = null;
            Field modelField = null;
            Field numCarsField = null;
            Constructor<?> constructor = null;
            Object carObject = null;

            try {
                makeField = carClass.getDeclaredField("make");
                makeField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Assert.fail("Make sure you have declared the `make` field in the Car class.");
            }

            try {
                modelField = carClass.getDeclaredField("model");
                modelField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Assert.fail("Make sure you have declared the `model` field in the Car class.");
            }

            try {
                numCarsField = carClass.getDeclaredField("numCars");
                numCarsField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Assert.fail("Make sure you have declared the `numCars` field in the Car class.");
            }

            try {
                constructor = carClass.getDeclaredConstructor(String.class, String.class);
                constructor.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Assert.fail("Make sure you have declared the constructor in the Car class with the correct parameters.");
            }

            int prevNumCars = -1;
            try {
                prevNumCars = (int) numCarsField.get(null);
            } catch (IllegalAccessException e) {
                DebugUtilities.failWithStackTrace(e.getCause());
            }

            final int EXPECTED_NUMCARS = prevNumCars + 1;

            try {
                carObject = constructor.newInstance(EXPECTED_MAKE, EXPECTED_MODEL);
            } catch (ReflectiveOperationException e) {
                DebugUtilities.failWithStackTrace(e.getCause());
            }

            int newNumCars = -1;
            try {
                newNumCars = (int) numCarsField.get(null);
            } catch (IllegalAccessException e) {
                DebugUtilities.failWithStackTrace(e.getCause());
            }

            String actualMake = null;
            String actualModel = null;

            try {
                actualMake = (String) makeField.get(carObject);
                actualModel = (String) modelField.get(carObject);
            } catch (IllegalAccessException e) {
                DebugUtilities.failWithStackTrace(e.getCause());
            }

            Assert.assertEquals("Make sure you are initializing the `model` field in the Car constructor.", EXPECTED_MAKE, actualMake);
            Assert.assertEquals("Make sure you are initializing the `make` field in the Car constructor.", EXPECTED_MODEL, actualModel);
            Assert.assertEquals("Make sure you are incrementing the `numCars` field when creating new Car objects.", EXPECTED_NUMCARS, newNumCars);
        }

    }


    /**
     * Tests for methods in the class.
     */
    public static class MethodTests {

        @Test
        @TestCase(name = "Car: `getMake` method declaration", points = 6.25)
        public void getMakeMethodDeclaration() {
            Method method = null;
            
            try {
                method = carClass.getDeclaredMethod("getMake");
                method.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Assert.fail("Make sure you have declared the `getMake` method in the Car class.");
            }
            
            Assert.assertEquals("Make sure the `getMake` method returns type `String`.", String.class, method.getReturnType());
            
            boolean isPublic = Modifier.isPublic(method.getModifiers());
            
            Assert.assertTrue("Make sure the `getMake` method is pubic.", isPublic);
        }

        @Test
        @TestCase(name = "Car: `getMake` method implementation", points = 6.25)
        public void getMakeMethodImplementation() {
            final String EXPECTED_MAKE = "Honda";
            String actualMake = null;
            Field makeField = null;
            Constructor<?> constructor = null;
            Object carObject = null;
            Method method = null;
            
            try {
                makeField = carClass.getDeclaredField("make");
                makeField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Assert.fail("Make sure you have declared the `make` field in the Car class.");
            }

            try {
                constructor = carClass.getDeclaredConstructor(String.class, String.class);
                constructor.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Assert.fail("Make sure you have declared the constructor in the Car class with the correct parameters.");
            }
            
            try {
                carObject = constructor.newInstance("", "");
            } catch(ReflectiveOperationException e) {
                DebugUtilities.failWithStackTrace(e.getCause());
            }

            try {
                method = carClass.getDeclaredMethod("getMake");
                method.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Assert.fail("Make sure you have declared the `getMake` method in the Car class.");
            }
            
            try {
                makeField.set(carObject, EXPECTED_MAKE);
            } catch (IllegalAccessException e) {
                DebugUtilities.failWithStackTrace(e.getCause());
            }

            try {
                actualMake = (String) method.invoke(carObject);
            } catch (ReflectiveOperationException e) {
                DebugUtilities.failWithStackTrace(e.getCause());
            }
            
            Assert.assertEquals("Make sure the `getMake` method returns the correct make.", EXPECTED_MAKE, actualMake);
        }

        @Test
        @TestCase(name = "Car: `getModel` method declaration", points = 6.25)
        public void getModelMethodDeclaration() {
            Method method = null;

            try {
                method = carClass.getDeclaredMethod("getModel");
                method.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Assert.fail("Make sure you have declared the `getModel` method in the Car class.");
            }

            Assert.assertEquals("Make sure the `getModel` method returns type `String`.", String.class, method.getReturnType());

            boolean isPublic = Modifier.isPublic(method.getModifiers());

            Assert.assertTrue("Make sure the `getModel` method is pubic.", isPublic);
        }

        @Test
        @TestCase(name = "Car: `getModel` method implementation", points = 6.25)
        public void getModelMethodImplementation() {
            final String EXPECTED_MODEL = "Civic";
            String actualModel = null;
            Field modelField = null;
            Constructor<?> constructor = null;
            Object carObject = null;
            Method method = null;

            try {
                modelField = carClass.getDeclaredField("model");
                modelField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Assert.fail("Make sure you have declared the `model` field in the Car class.");
            }

            try {
                constructor = carClass.getDeclaredConstructor(String.class, String.class);
                constructor.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Assert.fail("Make sure you have declared the constructor in the Car class with the correct parameters.");
            }

            try {
                carObject = constructor.newInstance("", "");
            } catch(ReflectiveOperationException e) {
                DebugUtilities.failWithStackTrace(e.getCause());
            }

            try {
                method = carClass.getDeclaredMethod("getModel");
                method.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Assert.fail("Make sure you have declared the `getModel` method in the Car class.");
            }

            try {
                modelField.set(carObject, EXPECTED_MODEL);
            } catch (IllegalAccessException e) {
                DebugUtilities.failWithStackTrace(e.getCause());
            }

            try {
                actualModel = (String) method.invoke(carObject);
            } catch (ReflectiveOperationException e) {
                DebugUtilities.failWithStackTrace(e.getCause());
            }

            Assert.assertEquals("Make sure the `getModel` method returns the correct model.", EXPECTED_MODEL, actualModel);
        }

        @Test
        @TestCase(name = "Car: `getNumCars` method declaration", points = 6.25)
        public void getNumCarsDeclaration() {
            Method method = null;

            try {
                method = carClass.getDeclaredMethod("getNumCars");
                method.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Assert.fail("Make sure you have declared the `getNumCars` method in the Car class.");
            }

            Assert.assertEquals("Make sure the `getNumCars` method returns type `int`.", int.class, method.getReturnType());

            boolean isPublic = Modifier.isPublic(method.getModifiers());

            Assert.assertTrue("Make sure the `getMake` method is pubic.", isPublic);

            boolean isStatic = Modifier.isStatic(method.getModifiers());

            Assert.assertTrue("Make sure the `getNumCars` method is static.", isStatic);
        }

        @Test
        @TestCase(name = "Car: `getNumCars` method implementation", points = 6.25)
        public void getNumCarsImplementation() {
            final int EXPECTED_NUMCARS = 5;
            int actualNumCars = -1;
            Field numCarsField = null;
            Method method = null;

            try {
                numCarsField = carClass.getDeclaredField("numCars");
                numCarsField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Assert.fail("Make sure you have declared the `numCars` field in the Car class.");
            }

            try {
                method = carClass.getDeclaredMethod("getNumCars");
                method.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Assert.fail("Make sure you have declared the `getNumCars` method in the Car class.");
            }

            try {
                numCarsField.set(null, EXPECTED_NUMCARS);
            } catch (IllegalAccessException e) {
                DebugUtilities.failWithStackTrace(e.getCause());
            }

            try {
                actualNumCars = (int) method.invoke(null);
            } catch (ReflectiveOperationException e) {
                DebugUtilities.failWithStackTrace(e.getCause());
            }

            Assert.assertEquals("Make sure the `getNumCars` method returns the correct number of cars.", EXPECTED_NUMCARS, actualNumCars);
        }

        @Test
        @TestCase(name = "Car: `setMake` method declaration", points = 6.25)
        public void setMakeDeclaration() {
            Method method = null;

            try {
                method = carClass.getDeclaredMethod("setMake", String.class);
                method.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Assert.fail("Make sure you have declared the `setMake` method in the Car class with the correct parameters.");
            }

            Assert.assertEquals("Make sure the `setMake` method returns type `void`.", void.class, method.getReturnType());

            boolean isPublic = Modifier.isPublic(method.getModifiers());

            Assert.assertTrue("Make sure the `setMake` method is pubic.", isPublic);
        }

        @Test
        @TestCase(name = "Car: `setMake` method implementation", points = 6.25)
        public void setMakeImplementation() {
            final String EXPECTED_MAKE = "Subaru";
            String actualMake = null;
            Field makeField = null;
            Constructor<?> constructor = null;
            Object carObject = null;
            Method method = null;

            try {
                makeField = carClass.getDeclaredField("make");
                makeField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Assert.fail("Make sure you have declared the `make` field in the Car class.");
            }

            try {
                constructor = carClass.getDeclaredConstructor(String.class, String.class);
                constructor.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Assert.fail("Make sure you have declared the constructor in the Car class with the correct parameters.");
            }

            try {
                carObject = constructor.newInstance("", "");
            } catch(ReflectiveOperationException e) {
                DebugUtilities.failWithStackTrace(e.getCause());
            }

            try {
                method = carClass.getDeclaredMethod("setMake", String.class);
                method.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Assert.fail("Make sure you have declared the `setMake` method in the Car class.");
            }
            
            try {
                method.invoke(carObject, EXPECTED_MAKE);
            } catch (ReflectiveOperationException e) {
                DebugUtilities.failWithStackTrace(e.getCause());
            }

            try {
                actualMake = (String) makeField.get(carObject);
            } catch (IllegalAccessException e) {
                DebugUtilities.failWithStackTrace(e.getCause());
            }

            Assert.assertEquals("Make sure the `setMake` method sets the `make` field.", EXPECTED_MAKE, actualMake);
        }

        @Test
        @TestCase(name = "Car: `setModel` method declaration", points = 6.25)
        public void setModelDeclaration() {
            Method method = null;

            try {
                method = carClass.getDeclaredMethod("setModel", String.class);
                method.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Assert.fail("Make sure you have declared the `setModel` method in the Car class with the correct parameters.");
            }

            Assert.assertEquals("Make sure the `setModel` method returns type `void`.", void.class, method.getReturnType());

            boolean isPublic = Modifier.isPublic(method.getModifiers());

            Assert.assertTrue("Make sure the `setModel` method is pubic.", isPublic);
        }

        @Test
        @TestCase(name = "Car: `setModel` method implementation", points = 6.25)
        public void setModelImplementation() {
            final String EXPECTED_MODEL = "F-150";
            String actualModel = null;
            Field modelField = null;
            Constructor<?> constructor = null;
            Object carObject = null;
            Method method = null;

            try {
                modelField = carClass.getDeclaredField("model");
                modelField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Assert.fail("Make sure you have declared the `model` field in the Car class.");
            }

            try {
                constructor = carClass.getDeclaredConstructor(String.class, String.class);
                constructor.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Assert.fail("Make sure you have declared the constructor in the Car class with the correct parameters.");
            }

            try {
                carObject = constructor.newInstance("", "");
            } catch(ReflectiveOperationException e) {
                DebugUtilities.failWithStackTrace(e.getCause());
            }

            try {
                method = carClass.getDeclaredMethod("setModel", String.class);
                method.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Assert.fail("Make sure you have declared the `setModel` method in the Car class.");
            }

            try {
                method.invoke(carObject, EXPECTED_MODEL);
            } catch (ReflectiveOperationException e) {
                DebugUtilities.failWithStackTrace(e.getCause());
            }

            try {
                actualModel = (String) modelField.get(carObject);
            } catch (IllegalAccessException e) {
                DebugUtilities.failWithStackTrace(e.getCause());
            }

            Assert.assertEquals("Make sure the `setModel` method sets the `model` field.", EXPECTED_MODEL, actualModel);
        }

    }

}
