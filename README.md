# Itertools-object-oriented
# Java Advanced Iterators & Student Statistics Tool

## 📚 Overview

This project showcases the implementation of advanced iterators in Java using **object-oriented programming (OOP)** and **lazy evaluation** principles. These iterators are then applied in a real-world application: a **Student Statistics Tool**, which processes and analyzes student data in a modular and extensible way.

---

## 🧩 Project Components

### 1. Advanced Iterators Library (`itertools/`)
Implements functional-style iterators using Java’s `Iterator<T>` interface and OOP principles.

- `MapIterator<T, R>`: Applies a transformation function.
- `FilterIterator<T>`: Filters elements based on a predicate.
- `TakeIterator<T>`: Limits output to the first N elements.
- `ZipIterator<A, B, R>`: Zips two iterators together.
- `ReduceOperation<T>`: Reduces elements using a binary operation.

### 2. Student Data Model (`studentapi/`)
Defines the core data classes:
- `Student`: Represents a student with fields like name, ID, and GPA.
- `StudentDataset`: Contains and loads student collections.

### 3. Student Statistics Engine (`studentstats/`)
- `StudentStatistics`: Provides operations like filtering students by performance, computing averages, and aggregating statistics using the custom iterators.

### 4. Test & Demo Program (`test/`)
- `Test.java`: Entry point of the application, demonstrates usage of all custom iterators and the student statistics tool.
- `Stats.java`: Additional examples/tests for iterators in use.
- May also contain subfolders like `itertools/` or `studentstats/` for test-specific versions.

---

## 🚀 How to Compile and Run

Make sure you're inside the `src/` directory before running the following commands.

### 1. Open terminal:
```bash
cd path/to/your/project/src

2. Compile all Java files:
javac $(find . -name "*.java")

3. Run the demo:
java test.Test ```