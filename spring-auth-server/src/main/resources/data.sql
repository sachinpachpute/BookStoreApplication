
INSERT INTO USER (USER_ID, FIRST_NAME, LAST_NAME, PASSWORD, USER_NAME, EMAIL)  VALUES
('1', 'Cores', 'DevD', '$2a$10$2XWkMz42.EApOBnx7nJaSupInwvBfPCGb5HZwWM.2RsC92joeAQzq', 'devd.cores', 'john.doe@gmail.com'),
('2', 'Admin', 'Admin', '$2a$10$br7HrUzeQQ0ddR2ogg7L1.aRQ1sGC1rud.mL8VQBEKaMkx1G5zXR6', 'admin.admin', 'admin@gmail.com'),
('3', 'Cores', 'DevD', '$2a$10$2XWkMz42.EApOBnx7nJaSupInwvBfPCGb5HZwWM.2RsC92joeAQzq', 'devaraj.reddy', 'devaraj.reddy@gmail.com'),
('4', 'Admin', 'Admin', '$2a$10$br7HrUzeQQ0ddR2ogg7L1.aRQ1sGC1rud.mL8VQBEKaMkx1G5zXR6', 'reddy.devaraj', 'reddy.devaraj@gmail.com'),
('5', 'Cores', 'DevD', '$2a$10$2XWkMz42.EApOBnx7nJaSupInwvBfPCGb5HZwWM.2RsC92joeAQzq', 'rale.reddy', 'rale.reddy@gmail.com'),
('6', 'Admin', 'Admin', '$2a$10$br7HrUzeQQ0ddR2ogg7L1.aRQ1sGC1rud.mL8VQBEKaMkx1G5zXR6', 'devd.reddy', 'devd.reddy@gmail.com'),
('7', 'Cores', 'DevD', '$2a$10$2XWkMz42.EApOBnx7nJaSupInwvBfPCGb5HZwWM.2RsC92joeAQzq', 'reddy.rale', 'reddy.rale@gmail.com'),
('8', 'Admin', 'Admin', '$2a$10$br7HrUzeQQ0ddR2ogg7L1.aRQ1sGC1rud.mL8VQBEKaMkx1G5zXR6', 'devd.bro', 'devd.bro@gmail.com');

INSERT INTO ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) VALUES ('10', 'STANDARD_USER', 'Standard User - Has no admin rights');
INSERT INTO ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) VALUES ('11', 'ADMIN_USER', 'Admin User - Has permission to perform admin tasks');
INSERT INTO ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) VALUES ('12', 'SELLER', 'Seller who can manage his inventory');
INSERT INTO ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) VALUES ('13', 'PRODUCT_OWNER', 'Product Owner');

INSERT INTO USER_ROLES(USER_ID, ROLE_ID) VALUES('1','10');
INSERT INTO USER_ROLES(USER_ID, ROLE_ID) VALUES('2','10');
INSERT INTO USER_ROLES(USER_ID, ROLE_ID) VALUES('2','11');
INSERT INTO USER_ROLES(USER_ID, ROLE_ID) VALUES('3','10');
INSERT INTO USER_ROLES(USER_ID, ROLE_ID) VALUES('8','13');
--INSERT INTO USER_ROLES(USER_ID, ROLE_ID) VALUES('5ad2f9f4-9018-4321-a08a-17fbc21887d9','11');

INSERT INTO oauth_client_details (CLIENT_ID, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, WEB_SERVER_REDIRECT_URI, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY, ADDITIONAL_INFORMATION, AUTOAPPROVE, RESOURCE_IDS) VALUES  ('93ed453e-b7ac-4192-a6d4-c45fae0d99ac','$2a$10$LulvWhcpUv8lFGmd9NbIG.PB46E5/tcivjGbELWMMWeBuDHKtrVKa', 'read,write',  'password,authorization_code,refresh_token,client_credentials', 'http://localhost:3000', 'ADMIN_USER', 259200, 604800, null, true,'web');
