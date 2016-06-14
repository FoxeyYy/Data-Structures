#include <CUnit/Basic.h>
#include <CUnit/Console.h>
#include "StringMap.h"

map *m = NULL;

int init_suite_success(void){
	m = createMap();
	return 0;
}

int init_suite_failure(void){
	return -1;
}

int clean_suite_success(void){
	return 0;
}

int clean_suite_failure(void){
	return -1;
}

void create(void){
	CU_ASSERT_PTR_NOT_NULL(m);
}

void add(void){
	addElement(m, "test", 3);
	int value = getValue(m, "test");
	CU_ASSERT_EQUAL(3, value);
}

void removeEl(void){
	addElement(m, "test2", 1);
	removeElement(m, "test2");
	int value = getValue(m, "test2");
	CU_ASSERT_EQUAL(-1, value);
}

void refac(void){
	for(char c = 'a'; c < 24; c++){
		addElement(m, &c, (int)c);
	}

	for(char c = 'a'; c < 24; c++){
		removeElement(m, &c);
	}

	int value = 999;
	for(char c = 'a'; c < 24; c++){
		value = getValue(m, &c);
		CU_ASSERT_EQUAL(-1, value);
		value = 999;
	}
}

int main(){
	CU_pSuite pSuite = NULL;

	if(CUE_SUCCESS != CU_initialize_registry()){
		return CU_get_error();
	}

	pSuite = CU_add_suite("Suite test", init_suite_success, clean_suite_success);
	if (NULL == pSuite){
		CU_cleanup_registry();
		return CU_get_error();
	}

	if(NULL == CU_add_test(pSuite, "add", add)
		|| NULL == CU_add_test(pSuite, "remove", removeEl)
		|| NULL == CU_add_test(pSuite, "refactor", refac)
		|| NULL == CU_add_test(pSuite, "create", create)){
		CU_cleanup_registry();
		return CU_get_error();
	}

	CU_basic_set_mode(CU_BRM_VERBOSE);
	CU_basic_run_tests();
	printf("\n");
	CU_basic_show_failures(CU_get_failure_list());
	printf("\n\n");

	CU_cleanup_registry();
	return CU_get_error();
}
