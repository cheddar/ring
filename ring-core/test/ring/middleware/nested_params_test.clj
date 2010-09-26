(ns ring.middleware.nested-params-test
  (:use clojure.test
        ring.middleware.nested-params))

(deftest nested-params-test
  (let [handler (wrap-nested-params :params)]
    (are [p r] (= (handler {:params p}) r)
      {"foo" "bar"}   {"foo" "bar"}
      {"x[y]" "z"}    {"x" {"y" "z"}}
      {"a[b][c]" "d"} {"a" {"b" {"c" "d"}}})))
