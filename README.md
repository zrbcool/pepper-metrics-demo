# pepper-metrics-demo
该demo主要用于展示pepper-metrics-x的各项监控能力，意在通过docker-compose的快速部署能力，让使用者能够在10分钟内快速的启动一套完整的demo，并能够直观的看到效果。

## play with docker
### install docker
```bash
curl -sSL https://get.daocloud.io/docker | sh
```
### install docker compose
```bash
curl -L https://get.daocloud.io/docker/compose/releases/download/1.24.1/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose
```
### other platforms
请参考：  
https://get.daocloud.io/#install-compose  
https://docs.docker.com/install/linux/docker-ce/ubuntu/  
https://docs.docker.com/compose/install/
### run this demo
```bash
docker-compose up
```
### play demo
- 访问grafana  
http://localhost:3000
- 访问prometheus  
http://localhost:9090
- 访问client的监控数据
    - metrics
    ```bash
    ➜  ~ docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' pepper-metrics-demo-client               
    172.20.0.5
    
    ➜  ~ curl 172.20.0.5:9146/metrics
    # HELP app_motan_request_out_err_counter_total  
    # TYPE app_motan_request_out_err_counter_total counter
    app_motan_request_out_err_counter_total{method="api.HelloService.sayHello(lang.String)",service="api.HelloService",} 5.0
    # HELP app_motan_request_out_summary_seconds  
    # TYPE app_motan_request_out_summary_seconds summary
    app_motan_request_out_summary_seconds{method="api.HelloService.sayHello(lang.String)",service="api.HelloService",quantile="0.9",} 0.010420224
    app_motan_request_out_summary_seconds{method="api.HelloService.sayHello(lang.String)",service="api.HelloService",quantile="0.99",} 0.012517376
    app_motan_request_out_summary_seconds{method="api.HelloService.sayHello(lang.String)",service="api.HelloService",quantile="0.999",} 0.218038272
    app_motan_request_out_summary_seconds{method="api.HelloService.sayHello(lang.String)",service="api.HelloService",quantile="0.99999",} 0.218038272
    app_motan_request_out_summary_seconds_count{method="api.HelloService.sayHello(lang.String)",service="api.HelloService",} 2245.0
    app_motan_request_out_summary_seconds_sum{method="api.HelloService.sayHello(lang.String)",service="api.HelloService",} 16.335338483
    # HELP app_motan_request_out_summary_seconds_max  
    # TYPE app_motan_request_out_summary_seconds_max gauge
    app_motan_request_out_summary_seconds_max{method="api.HelloService.sayHello(lang.String)",service="api.HelloService",} 0.21656222
    # HELP app_motan_request_out_concurrent_gauge  
    # TYPE app_motan_request_out_concurrent_gauge gauge
    app_motan_request_out_concurrent_gauge{method="api.HelloService.sayHello(lang.String)",service="api.HelloService",} 0.0
    ```
    - 监控日志查看
    ```bash
    ➜  ~ docker exec pepper-metrics-demo-client tail -f logs/demo-client/performance.log
    12:16:30 [perf-motan:outgoing:20190826121630] | Metrics                                                                     Concurrent Count(Err/Sum)   P90(ms)   P99(ms)  P999(ms)   Max(ms)     Qps | 
    12:16:30 [perf-motan:outgoing:20190826121630] | api.HelloService.sayHello(lang.String)                                               0         0/3740       9.9      10.4      10.4      15.7    62.3 | 
    12:16:30 [perf-motan:outgoing:20190826121630] ---------------------------------------------------------------------------------------------------------------------------------------------------------
    12:17:30 [perf-motan:incoming:20190826121730] ---------------------------------------------------------------------------------------------------------------------------------------------------------
    12:17:30 [perf-motan:incoming:20190826121730] | Metrics                                                                     Concurrent Count(Err/Sum)   P90(ms)   P99(ms)  P999(ms)   Max(ms)     Qps | 
    12:17:30 [perf-motan:incoming:20190826121730] ---------------------------------------------------------------------------------------------------------------------------------------------------------
    12:17:30 [perf-motan:outgoing:20190826121730] ---------------------------------------------------------------------------------------------------------------------------------------------------------
    12:17:30 [perf-motan:outgoing:20190826121730] | Metrics                                                                     Concurrent Count(Err/Sum)   P90(ms)   P99(ms)  P999(ms)   Max(ms)     Qps | 
    12:17:30 [perf-motan:outgoing:20190826121730] | api.HelloService.sayHello(lang.String)                                               0         0/3725       9.9      10.4      10.4      12.0    62.1 | 
    12:17:30 [perf-motan:outgoing:20190826121730] ---------------------------------------------------------------------------------------------------------------------------------------------------------
    ```

- 访问server的监控数据
    - metrics
    ```bash
    ➜  ~ curl 172.20.0.2:9146/metrics
    # HELP app_motan_request_in_summary_seconds  
    # TYPE app_motan_request_in_summary_seconds summary
    app_motan_request_in_summary_seconds{method="api.HelloService.sayHello(lang.String)",service="api.HelloService",quantile="0.9",} 0.009371648
    app_motan_request_in_summary_seconds{method="api.HelloService.sayHello(lang.String)",service="api.HelloService",quantile="0.99",} 0.009371648
    app_motan_request_in_summary_seconds{method="api.HelloService.sayHello(lang.String)",service="api.HelloService",quantile="0.999",} 0.009371648
    app_motan_request_in_summary_seconds{method="api.HelloService.sayHello(lang.String)",service="api.HelloService",quantile="0.99999",} 0.009371648
    app_motan_request_in_summary_seconds_count{method="api.HelloService.sayHello(lang.String)",service="api.HelloService",} 5182.0
    app_motan_request_in_summary_seconds_sum{method="api.HelloService.sayHello(lang.String)",service="api.HelloService",} 26.771158848
    # HELP app_motan_request_in_summary_seconds_max  
    # TYPE app_motan_request_in_summary_seconds_max gauge
    app_motan_request_in_summary_seconds_max{method="api.HelloService.sayHello(lang.String)",service="api.HelloService",} 0.020925499
    # HELP app_motan_request_in_concurrent_gauge  
    # TYPE app_motan_request_in_concurrent_gauge gauge
    app_motan_request_in_concurrent_gauge{method="api.HelloService.sayHello(lang.String)",service="api.HelloService",} 1.0
    ```
    - 监控日志查看
    ```bash
    ➜  ~ docker exec pepper-metrics-demo-server tail -f logs/demo-server/performance.log
    12:18:29 [perf-motan:outgoing:20190826121829] ---------------------------------------------------------------------------------------------------------------------------------------------------------
    12:18:29 [perf-motan:outgoing:20190826121829] | Metrics                                                                     Concurrent Count(Err/Sum)   P90(ms)   P99(ms)  P999(ms)   Max(ms)     Qps | 
    12:18:29 [perf-motan:outgoing:20190826121829] ---------------------------------------------------------------------------------------------------------------------------------------------------------
    12:19:29 [perf-motan:incoming:20190826121929] ---------------------------------------------------------------------------------------------------------------------------------------------------------
    12:19:29 [perf-motan:incoming:20190826121929] | Metrics                                                                     Concurrent Count(Err/Sum)   P90(ms)   P99(ms)  P999(ms)   Max(ms)     Qps | 
    12:19:29 [perf-motan:incoming:20190826121929] | api.HelloService.sayHello(lang.String)                                               1         0/3723       9.4       9.4       9.4       9.4    62.0 | 
    12:19:29 [perf-motan:incoming:20190826121929] ---------------------------------------------------------------------------------------------------------------------------------------------------------
    12:19:29 [perf-motan:outgoing:20190826121929] ---------------------------------------------------------------------------------------------------------------------------------------------------------
    12:19:29 [perf-motan:outgoing:20190826121929] | Metrics                                                                     Concurrent Count(Err/Sum)   P90(ms)   P99(ms)  P999(ms)   Max(ms)     Qps | 
    12:19:29 [perf-motan:outgoing:20190826121929] ---------------------------------------------------------------------------------------------------------------------------------------------------------
    ```
