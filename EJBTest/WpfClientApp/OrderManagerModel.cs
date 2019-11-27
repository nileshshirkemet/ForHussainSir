using System;
using System.Threading.Tasks;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;

namespace WpfClientApp
{
    [DataContract]
    public class OrderInput
    {
        [DataMember(Name = "customerId")]
        public string CustomerId { get; set; }

        [DataMember(Name = "productNo")]
        public int ProductNo { get; set; }

        [DataMember(Name = "quantity")]
        public int Quantity { get; set; }
    }

    [DataContract]
    public class OrderOutput
    {
        [DataMember(Name = "orderNo")]
        public int OrderNo { get; set; }
    }

    [ServiceContract]
    public interface IOrderManager
    {
        [OperationContract]
        [WebInvoke(Method = "POST", UriTemplate = "sales/order", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        OrderOutput CreateOrder(OrderInput input);
    }

    public class OrderManagerModel
    {
        private Uri serviceUri = new Uri(Properties.Settings.Default.ServerAppAddress);

        public int SubmitOrder(string customerId, int productNo, int quantity)
        {
            using(var service = new WebChannelFactory<IOrderManager>(serviceUri))
            {
                IOrderManager client = service.CreateChannel();
                OrderOutput result = client.CreateOrder(new OrderInput
                {
                    CustomerId = customerId,
                    ProductNo = productNo,
                    Quantity = quantity
                });

                return result.OrderNo;
            }
        }

        public Task<int> SubmitOrderAsync(string customerId, int productNo, int quantity)
        {
            return Task<int>.Run(() => SubmitOrder(customerId, productNo, quantity));
        }
    }
}
