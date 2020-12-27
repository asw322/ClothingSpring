CREATE TABLE CLOSET (
    ID VARCHAR(100) NOT NULL, 
    PRODUCT_ID VARCHAR(100) NOT NULL,
    CONSTRAINT FK_PRODUCT
      FOREIGN KEY(PRODUCT_ID) 
	  REFERENCES PRODUCT(PRODUCT_ID),
    CONSTRAINT FK_USERDATA
      FOREIGN KEY(ID)
      REFERENCES USER_DATA(ID)
);