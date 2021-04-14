# Object-Oriented Programming Example
This repo contains example tests for testing labs which observe the properties of fields, constructors, classes, and methods. Test cases are also included to give you an idea of how to correctly invoke methods and observe fields.

## Usage
To test the instructor solution:
```bash
mvn -Denv=dev test
```

To test the student solution:
```bash
mvn -Denv=prod test
```

To create a portable JAR:
```bash
mvn -DskipTests package
```

## License
Licensed under the [MIT License](LICENSE).
