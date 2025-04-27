variable "mysql_root_password" {
  description = "Root password for MariaDB"
  type        = string
  sensitive   = true
}

variable "mysql_database" {
  description = "Database name for MariaDB"
  type        = string
}
