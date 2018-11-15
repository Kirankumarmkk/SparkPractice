package scopeB {
  class PublicClass1B extends scopeA.PublicClass1
  class UsingClass(val publicClass: scopeA.PublicClass1) {
    def method = "UsingClass:" +
      " field: " + publicClass.publicField + " nested field: " + publicClass.nested.nestedField
  }
}