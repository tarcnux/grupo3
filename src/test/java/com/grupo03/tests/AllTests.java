package com.grupo03.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({PersonDaoTest.class, EventRoomDaoTest.class, CoffeeRoomDaoTest.class})
public class AllTests {}
