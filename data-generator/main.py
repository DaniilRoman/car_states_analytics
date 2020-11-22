
from openapi_client.api_client import ApiClient
from openapi_client.configuration import Configuration
from openapi_client.api.test_api import TestApi


if __name__ == '__main__':
    api_client = ApiClient()
    configuration = Configuration(
        host="http://localhost:9090"
    )
    api_client.configuration = configuration
    test_api = TestApi(api_client)

    print(test_api.get_test(3232))
