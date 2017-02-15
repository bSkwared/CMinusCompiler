echo "Running jflex on CMinus.flex..."
./jflex/jflex-1.6.1/bin/jflex CMinus.flex

echo "Compiling CMinusLex.java..."
javac CMinusLex.java

