terraform {
  required_providers {
    docker = {
      source  = "kreuzwerker/docker"
      version = "~> 3.0.2"
    }
  }
}

provider "docker" {}

resource "docker_volume" "mariadb_data" {
  name = "mariadb_data"
}

resource "docker_image" "mariadb" {
  name = "mariadb:latest"
}

resource "local_file" "init_sql" {
  filename = "${path.module}/init.sql"
  content  = <<-EOT
    -- 초기화 SQL 예시
    CREATE TABLE IF NOT EXISTS sample_table (
      id INT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(255) NOT NULL
    );
  EOT
}

resource "docker_container" "mariadb" {
  name  = "mariadb-container"
  image = docker_image.mariadb.name

  ports {
    internal = 3306
    external = 3306
  }

  env = [
    "MYSQL_ROOT_PASSWORD=${var.mysql_root_password}",
    "MYSQL_DATABASE=${var.mysql_database}"
  ]

  volumes {
    volume_name    = docker_volume.mariadb_data.name
    container_path = "/var/lib/mysql"
  }

  volumes {
    host_path      = abspath("${path.module}/init.sql")
    container_path = "/docker-entrypoint-initdb.d/init.sql"
    read_only      = true
  }

  depends_on = [local_file.init_sql]
}
