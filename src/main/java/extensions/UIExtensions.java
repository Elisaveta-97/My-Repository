package extensions;

import annotations.Driver;
import factory.FactoryDriver;

import java.lang.reflect.Field;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.openqa.selenium.WebDriver;

import java.lang.annotation.Annotation;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class UIExtensions implements BeforeEachCallback {

  private WebDriver driver;

  private List<Field> getFieldsByAnnotation(Class<? extends Annotation> annotation, Class<?> testClass) {
    Field[] fields = testClass.getFields();
    return Arrays.stream(fields)
        .filter((Field field) -> field.isAnnotationPresent(annotation) && field.getType().getName().equals(WebDriver.class))
        .collect(Collectors.toList());

  }

  @Override
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    driver = new FactoryDriver().getDriver();

    List<Field> fields = this.getFieldsByAnnotation(Driver.class, extensionContext.getTestClass().get());

    for (Field field : fields) {
      AccessController.doPrivileged((PrivilegedAction<Void>)
          () -> {
            field.setAccessible(true);
            try {
              field.set(extensionContext.getTestInstance().get(), driver);
            } catch (IllegalAccessException e) {
              throw new RuntimeException(e);
            }
            return null;
          }
      );
    }
  }
}

