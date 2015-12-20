package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GetBuilderTest.class, ResSetTest.class, CompareBuilderTest.class,
	LogicBuilderTest.class, SortBuilderTest.class, ColumnSortTest.class, MinBuilderTest.class,
	MaxBuilderTest.class, CountBuilderTest.class})
public class AllTests {}