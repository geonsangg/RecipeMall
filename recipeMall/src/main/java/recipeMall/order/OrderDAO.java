package recipeMall.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import recipeMall.mypage.CartVO;
import recipeMall.user.UserVO;

public class OrderDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public OrderDAO() {
		try {
	         Context ctx = new InitialContext();
	         Context envContext = (Context) ctx.lookup("java:/comp/env");
	         dataFactory = (DataSource) envContext.lookup("jdbc/oracle"); // "읽어올 데이터베이스"
	      } catch (Exception e) {
	         System.out.println("DB연결 오류");
	      }
	}
	// 고객 조회 메서드
	   public List<UserVO> selectUserInfo(String id) {
	      List<UserVO> userList = new ArrayList<UserVO>();
	      try {
	         conn = dataFactory.getConnection();
	         String query = "SELECT name, phone, userAddr, userAddr2 from usertbl where id=?";
	         pstmt = conn.prepareStatement(query);
	         pstmt.setString(1, id);
	         ResultSet rs = pstmt.executeQuery();
	         rs.next();
	         String name = rs.getString("name");
	         String phone = rs.getString("phone");
	         String userAddr = rs.getString("userAddr");
	         String userAddr2 = rs.getString("userAddr2");
	         UserVO userVO = new UserVO();
	         userVO.setName(name);
	         userVO.setPhone(phone);
	         userVO.setUserAddr(userAddr);
	         userVO.setUserAddr2(userAddr2);
	         userList.add(userVO);
	         rs.close();
	         pstmt.close();
	         conn.close();
	      }catch (Exception e) {
	         System.out.println("고객 조회 처리중 에러");
	         e.printStackTrace();
	      }
	      return userList;
	   }
	   
	   // 장바구니 조회 메서드 배열 VER
	   public List<CartVO> selectCartInfos(String id, String[] prodNames) {
	      List<CartVO> cartList = new ArrayList<CartVO>();
	      try { 
	         conn = dataFactory.getConnection();
	         String query = "SELECT * FROM carttbl WHERE prodName=?";
	         for (int i = 1; i < prodNames.length; i++) {
	        	 query += " OR prodName=?";
	         }
	         query += " AND id=?";
	         pstmt = conn.prepareStatement(query);
	         pstmt.setString(1, prodNames[0]);
	         for (int i = 1; i < prodNames.length; i++) {
	        	 pstmt.setString(i + 1, prodNames[i]);
	         }
	         pstmt.setString(prodNames.length+1, id);
	         ResultSet rs = pstmt.executeQuery();
	         while(rs.next ()) {
	            int prodCode = rs.getInt("prodCode");
	            int cartCount = rs.getInt("cartCount");
	            int prodPrice = rs.getInt("prodPrice");
	            String prodImage = rs.getString("prodImage");
	            String prodName = rs.getString("prodName");
	            CartVO cartVO = new CartVO();
	            cartVO.setProdCode(prodCode);
	            cartVO.setCartCount(cartCount);
	            cartVO.setProdPrice(prodPrice);
	            cartVO.setProdImage(prodImage);
	            cartVO.setProdName(prodName);
	            cartList.add(cartVO);
	         }
	         rs.close();
	         pstmt.close();
	         conn.close();
	      }catch (Exception e) {
	         System.out.println("장바구니 조회 처리중 에러");
	         e.printStackTrace();
	      }
	      return cartList;
	   }
	   
	   // 주문 조회 메서드
	   public List<OrderVO> selectOrder(String id) {
		   List<OrderVO> orderList = new ArrayList<OrderVO>();
		   try {
			   conn = dataFactory.getConnection();
			   String query = "SELECT * FROM (SELECT o.orderCode, o.deliveryFee, o.allOrderPrice, o.shipAddr, o.shipAddr2, o.requestMsg ,u.name, u.phone FROM ordertbl o INNER JOIN usertbl u ON o.id = u.id WHERE u.id=? ORDER BY o.orderCode DESC) WHERE ROWNUM = 1";
			   pstmt = conn.prepareStatement(query);
		       pstmt.setString(1, id);
		       ResultSet rs = pstmt.executeQuery();
		       rs.next();
		       int orderCode = rs.getInt("orderCode");
		       int deliveryFee = rs.getInt("deliveryFee");
		       int allOrderPrice = rs.getInt("allOrderPrice");
		       String shipAddr = rs.getString("shipAddr");
		       String shipAddr2 = rs.getString("shipAddr2");
		       String requestMsg = rs.getString("requestMsg");
		       String name = rs.getString("name");
		       String phone = rs.getString("phone");
		       OrderVO orderVO = new OrderVO();
		       orderVO.setOrderCode(orderCode);
		       orderVO.setDeliveryFee(deliveryFee);
		       orderVO.setAllOrderPrice(allOrderPrice);
		       orderVO.setShipAddr(shipAddr);
		       orderVO.setShipAddr2(shipAddr2);
		       orderVO.setRequestMsg(requestMsg);
		       orderVO.setName(name);
		       orderVO.setPhone(phone);
		       orderList.add(orderVO);
		       rs.close();
		       pstmt.close();
		       conn.close();
		   }catch (Exception e) {
			   System.out.println("주문 조회 처리중 에러");
		       e.printStackTrace();
		}
		   return orderList;
	   }
	   
	   //상세 주문 조회 메서드
	   public List<OrderDVO> selectDetail(int orderCode) {
		   List<OrderDVO> detailList = new ArrayList<OrderDVO>();
		   try {
			   conn = dataFactory.getConnection();
			   String query = "select p.prodName, o.orderAmount, p.prodCode from orderDtbl o INNER JOIN producttbl p ON o.prodCode = p.prodCode WHERE orderCode = ? ";
			   pstmt = conn.prepareStatement(query);
		       pstmt.setInt(1, orderCode);
		       ResultSet rs = pstmt.executeQuery();
		       while(rs.next ()) {
		    	   String prodName = rs.getString("prodName");
		    	   int orderAmount = rs.getInt("orderAmount");
		    	   int prodCode= rs.getInt("prodCode");
		    	   OrderDVO orderDVO = new OrderDVO();
		    	   orderDVO.setProdName(prodName);
		    	   orderDVO.setOrderAmount(orderAmount);
		    	   orderDVO.setProdCode(prodCode);
		    	   detailList.add(orderDVO);
		       }
		       rs.close();
		       pstmt.close();
		       conn.close();
		   }catch (Exception e) {
			   System.out.println("상세 주문 조회 처리중 에러");
		       e.printStackTrace();
		}
		   return detailList;
	   }
	   
	   //구매한 상품 장바구니 삭제 메서드
	   public void deleteCart(String id) {
		   try {
			   conn = dataFactory.getConnection();
			   String query = "DELETE FROM carttbl WHERE prodCode IN (SELECT odt.prodCode FROM orderdtbl odt INNER JOIN ordertbl ot ON odt.orderCode = ot.orderCode "
					   + "WHERE ot.orderCode = (SELECT MAX(orderCode) FROM ordertbl WHERE id = ?))";
			   pstmt = conn.prepareStatement(query);
			   pstmt.setString(1, id);
			   pstmt.executeUpdate();
			   pstmt.close();
			   conn.close();
		   }catch (Exception e) {
			   System.out.println("장바구니 삭제 처리중 에러");
		       e.printStackTrace();
		   }
	   }
	  //구매한 상품 판매개수 추가 메서드
	   public void updateProd(Map<String, Object> orderMap) {
		   String id = (String) orderMap.get("id");
		   String[] prodCodeStrArray = (String[]) orderMap.get("prodCode");
		   String[] cartCountStrArray = (String[]) orderMap.get("cartCount");

		   int[] prodCode = new int[prodCodeStrArray.length];
		   int[] cartCount = new int[cartCountStrArray.length];

		   for (int i = 0; i < prodCodeStrArray.length; i++) {
		       prodCode[i] = Integer.parseInt(prodCodeStrArray[i]);
		       cartCount[i] = Integer.parseInt(cartCountStrArray[i]);
		   }
		   try {
			   conn = dataFactory.getConnection();
			   String query = "UPDATE producttbl SET prodCount = prodCount + ? WHERE prodCode = ?";
			   pstmt = conn.prepareStatement(query);
			   for(int i=0; i<prodCode.length; i++) { 
				   pstmt.setInt(1, cartCount[i]);
				   pstmt.setInt(2, prodCode[i]); 				   
				   pstmt.executeUpdate();
			   }
		   }catch (Exception e) {
			   System.out.println("구매한 상품 판매개수 추가 처리중 에러");
		       e.printStackTrace();
		}
	   }
	   
	 //주문 테이블 삽입 메서드
	   public void insertOrder(Map<String, Object> orderMap) {
		   String id = (String) orderMap.get("id");
		   String stringallProdPrice = (String) orderMap.get("allProdPrice");
		   String NoallProdPrice = stringallProdPrice.replace(",", "");
		   int allProdPrice = Integer.parseInt(NoallProdPrice);
		   String userAddr = (String) orderMap.get("userAddr");
		   String userAddr2 = (String) orderMap.get("userAddr2");
		   String deliveryMSG = (String) orderMap.get("deliveryMSG");
		   try {
			   conn = dataFactory.getConnection();
			   String query = "INSERT INTO ordertbl(orderCode, id, orderStatus, paymentStatus, allOrderPrice, deliveryFee, shipAddr, shipAddr2, requestMSG) values(ORDERSEQ.nextval, ?, 1, 1, ?, 2500, ?, ?, ?)";
			   pstmt = conn.prepareStatement(query);
			   pstmt.setString(1, id);
			   pstmt.setInt(2, allProdPrice);
			   pstmt.setString(3, userAddr);
			   pstmt.setString(4, userAddr2);
			   pstmt.setString(5, deliveryMSG);
			   pstmt.executeUpdate();
			   
		   }catch (Exception e) {
			   System.out.println("주문 테이블 삽입 처리중 에러");
		       e.printStackTrace();
			}finally {
		        try {
		            if (pstmt != null) {
		                pstmt.close();
		            }
		            if (conn != null) {
		                conn.close();
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
			}
	   }		   
	   
	   //주문 상세 테이블 삽입 메서드
	   public void insertOrderD(Map<String, Object> orderMap) {
		   String id = (String) orderMap.get("id");
		   String[] prodCodeStrArray = (String[]) orderMap.get("prodCode");
		   String[] cartCountStrArray = (String[]) orderMap.get("cartCount");

		   int[] prodCode = new int[prodCodeStrArray.length];
		   int[] cartCount = new int[cartCountStrArray.length];

		   for (int i = 0; i < prodCodeStrArray.length; i++) {
		       prodCode[i] = Integer.parseInt(prodCodeStrArray[i]);
		       cartCount[i] = Integer.parseInt(cartCountStrArray[i]);
		   }
		   	Connection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		   try {
			   conn = dataFactory.getConnection(); 
			   String Maxquery = "SELECT MAX(orderCode) AS maxOrderCode FROM ordertbl WHERE id = ?";
			   pstmt = conn.prepareStatement(Maxquery);
			   pstmt.setString(1, id);
			   rs = pstmt.executeQuery();
			   
			   int maxOrderCode = 0;
			   if (rs.next()) {
			       maxOrderCode = rs.getInt("maxOrderCode");
			   }
			   
			   String insertQuery = "INSERT INTO orderDtbl (orderCode, prodCode, orderAmount) VALUES (?, ?, ?)";
			   pstmt = conn.prepareStatement(insertQuery);
			   for(int i=0; i<prodCode.length; i++) {
				   pstmt.setInt(1, maxOrderCode); 
				   pstmt.setInt(2, prodCode[i]); 
				   pstmt.setInt(3, cartCount[i]);
				   pstmt.executeUpdate();
			   }
			   pstmt.close();
			   conn.close(); 
			   rs.close();
		   }catch (Exception e) {
			   System.out.println("주문 상세 테이블 삽입 처리중 에러");
		       e.printStackTrace();
		}
	   }
}
