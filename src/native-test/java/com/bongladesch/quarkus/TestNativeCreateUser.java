package com.bongladesch.quarkus;

import com.bongladesch.quarkus.usecases.TestCreateUserAccount;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class TestNativeCreateUser extends TestCreateUserAccount {

    // Execute the same tests but in native mode.
}