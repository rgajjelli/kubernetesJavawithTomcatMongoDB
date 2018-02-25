curl -LO https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl
chmod +x ./kubectl
sudo mv ./kubectl /usr/local/bin/kubectl


# ** Configure kubectl on the ClientNodes **

#kubectl config set-cluster default-cluster --server=http://kubemaster:8443 --insecure-skip-tls-verify=true
kubectl config set-cluster default-cluster --server=http://kubemaster:8080 --insecure-skip-tls-verify=true
kubectl config set-context default-cluster --cluster=default-cluster --user=default-admin
kubectl config use-context default-context

# *** TEST from Jenkins Server ***
kubectl get nodes
