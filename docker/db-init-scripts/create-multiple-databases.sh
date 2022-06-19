#!/bin/bash

set -euf

TIME_COMMAND="date -u +'%Y-%m-%d %H:%M:%S'"

function create_user_and_database() {
  local database=$1 user=$1 password=$2

  echo "$(eval "${TIME_COMMAND}") [INFO]    Creating user ('$user':'$password') and database ('$database') ..."
  # shellcheck disable=SC2016,SC2086
  psql -v ON_ERROR_STOP=1 --dbname "$POSTGRES_DB" --username "$POSTGRES_USER" \
    -c "CREATE USER ${user} WITH PASSWORD '${password}';" \
    -c "CREATE DATABASE ${database};" \
    -c "GRANT ALL PRIVILEGES ON DATABASE ${database} TO ${user};"

  echo "$(eval "${TIME_COMMAND}") [INFO]    Creating extension (uuid-ossp) for database ('$database') ..."
  psql -v ON_ERROR_STOP=1 --dbname "$database" --username "${user}" \
    -c "CREATE SCHEMA ${database} AUTHORIZATION ${user};" \
    -c "CREATE EXTENSION IF NOT EXISTS \"uuid-ossp\" SCHEMA ${database};"
}

if [[ -n "$POSTGRES_MULTIPLE_DATABASES" ]]; then

  user_credentials_array=$(echo "$POSTGRES_MULTIPLE_DATABASES" | tr ',' ' ')
  echo "$(eval "${TIME_COMMAND}") [INFO]  Multiple database creation is requested: '$user_credentials_array'"

  for user_credentials in ${user_credentials_array}; do
    # shellcheck disable=SC2046
    create_user_and_database $(echo "$user_credentials" | tr ':' ' ')
  done

  echo "$(eval "${TIME_COMMAND}") [INFO]  Multiple databases are created."
fi