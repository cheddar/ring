(ns ring.middleware.nested-params)

(defn parse-nested-keys
  [param-name]
  (let [[_ k ks] (re-matches #"(.*?)(\[.*?\]*)" param-name)
        keys     (map second (re-seq #"\[(.*?)\]" ks))]
    (cons k keys)))

(defn- nest-params
  [params parse]
  (reduce
    (fn [m [k v]]
      (assoc-in m (parse k) v))
    {}
    params))

(defn wrap-nested-params
  "Converts a flat map of parameters into nested maps."
  [handler & [opts]]
  (fn [request]
    (let [parse   (:key-parser opts parse-nested-keys)
          request (update-in request [:params] nest-params parse)]
      (handler request))))
