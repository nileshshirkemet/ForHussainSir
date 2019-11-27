using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

namespace WpfClientApp
{
    public class MainWindowViewModel : MvvmSupport.ViewModelBase
    {
        private OrderManagerModel model = new OrderManagerModel();

        public string customerId;
        public string CustomerId
        {
            get => customerId;
            set => SetProperty(ref customerId, value);
        }

        private int productNo;
        public int ProductNo
        {
            get => productNo;
            set => SetProperty(ref productNo, value);
        }

        private int quantity;
        public int Quantity
        {
            get => quantity;
            set => SetProperty(ref quantity, value);
        }

        public string orderMessage = "Ready";
        public string OrderMessage
        {
            get => orderMessage;
            set => SetProperty(ref orderMessage, value);
        }

        private bool Order_CanExecute(object parameter)
        {
            return customerId?.Length > 4;
        }

        private async void Order_Execute(object parameter)
        {
            try
            {
                int orderNo = await model.SubmitOrderAsync(customerId, productNo, quantity);
                OrderMessage = $"New order number is {orderNo}";
            }
            catch
            {
                OrderMessage = "Order failed!";
            }
        }

        public ICommand Order => DispatchCommand();


    }
}
