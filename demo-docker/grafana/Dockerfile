FROM grafana/grafana

COPY grafana.ini /etc/grafana/grafana.ini
COPY provisioning.yml /etc/grafana/provisioning/dashboards/provisioning.yml
COPY datasource.yml /etc/grafana/provisioning/datasources/datasource.yml

RUN mkdir /var/lib/grafana/dashboards

COPY *.json /var/lib/grafana/dashboards/

