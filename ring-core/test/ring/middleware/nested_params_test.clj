(ns ring.middleware.nested-params-test
  (:use clojure.test
        ring.middleware.nested-params))

(deftest nested-params-test
  (let [handler (wrap-nested-params :params)]
    (is (= (handler {:params {"foo[bar]" "baz"}})
           {"foo" {"bar" "baz"}}))))
