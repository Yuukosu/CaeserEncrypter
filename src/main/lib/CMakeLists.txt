cmake_minimum_required(VERSION 3.24)
project(caesar C)

set(CMAKE_C_STANDARD 17)

add_library(${PROJECT_NAME} SHARED caesar.c caesar.h)
