cmake_minimum_required(VERSION 3.22.1)
project("biometric-client")

# Build a shared library named "security-lib" from your source file.
add_library(security-lib SHARED
        security-lib.cpp
)

# Locate the Android log library.
find_library(log-lib log)

# Link the log library to your native library.
target_link_libraries(security-lib ${log-lib})
