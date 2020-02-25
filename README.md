![CI](https://github.com/mshanu/idli/workflows/CI/badge.svg)

# idli
You get data with many columns as file, mostly csv. Now you would like to import the data to a database. Creating a table going through all the columns understanding the type is a pain. 
Idli helps you to generate create statement based on the data (sampling) for a database of your choice.

 
#### Support
Currently it takes only csv file with first line as header and generates the script for the following dbs
* Oracle
* Mysql

### Instructions to Run
git clone
`./gradlew run --args "file=<path_to_file> db=mysql"`
