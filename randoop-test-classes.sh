java -Xmx3000m -classpath ./target/classes:randoop-all-4.3.2.jar randoop.main.Main gentests --classlist=classes-to-test.txt --output-limit=350 --junit-output-dir=test/randoop-tests