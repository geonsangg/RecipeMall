package recipeMall.mypage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import recipeMall.board.FreeBoardReplyVO;
import recipeMall.board.FreeBoardVO;
import recipeMall.order.OrderDVO;
import recipeMall.order.OrderVO;
import recipeMall.recipe.RecipeVO;
import recipeMall.shopping.ProductVO;

public class MypageDAO {
   private Connection conn;
   private PreparedStatement pstmt;
   private DataSource dataFactory; // 서버 가동시 db정보 전달, 클라 요청할 때마다 db연결

   public MypageDAO() {
      try {
         Context ctx = new InitialContext();
         Context envContext = (Context) ctx.lookup("java:/comp/env");
         dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
      } catch (Exception e) {
         System.out.println("DB연결 오류");
      }
   }

   // 배송지 목록 메서드
   public List<ShippingAddrVO> listShippingAddr(String id) {
      List<ShippingAddrVO> shippigAddrList = new ArrayList();
      try {
         conn = dataFactory.getConnection();
         String query = "select shippingName,shippingTitle,shippingZonecode,shippingAddress,shippingAddressSub,shippingPhone,shippingCellphone from shippingaddrtbl where id=?";
         System.out.println(query);
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, id);
         ResultSet rs = pstmt.executeQuery();
         while (rs.next()) {
            String shippingName = rs.getString("shippingName");
            String ShippingTitle = rs.getString("ShippingTitle");
            int shippingZonecode = rs.getInt("shippingZonecode");
            String shippingAddress = rs.getString("shippingAddress");
            String shippingAddressSub = rs.getString("shippingAddressSub");
            String shippingPhone = rs.getString("shippingPhone");
            String shippingCellphone = rs.getString("shippingCellphone");
            ShippingAddrVO shipVo = new ShippingAddrVO(shippingName, ShippingTitle, shippingZonecode,
                  shippingAddress, shippingAddressSub, shippingPhone, shippingCellphone);
            shippigAddrList.add(shipVo);
         }
         rs.close();
         pstmt.close();
         conn.close();
      } catch (Exception e) {
         System.out.println("배송지 목록 가져오는중에러!");
         e.printStackTrace();
      }
      return shippigAddrList;
   }

   // 배송지추가메서드
   public void addShipping(ShippingAddrVO shipVo) {
      try {
         conn = dataFactory.getConnection();
         String shippingName = shipVo.getShippingName();
         String shippingTitle = shipVo.getShippingTitle();
         String id = shipVo.getId();
         int shippingZonecode = shipVo.getShippingZonecode();
         String shippingAddress = shipVo.getShippingAddress();
         String shippingAddressSub = shipVo.getShippingAddressSub();
         String shippingPhone = shipVo.getShippingPhone();
         String shippingCellphone = shipVo.getShippingCellphone();
         String query = "insert into shippingaddrtbl(shippingName,shippingTitle,id,shippingZonecode,shippingAddress,"
               + " shippingAddressSub,shippingPhone,shippingCellphone) values(?,?,?,?,?,?,?,?)";
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, shippingName);
         pstmt.setString(2, shippingTitle);
         pstmt.setString(3, id);
         pstmt.setInt(4, shippingZonecode);
         pstmt.setString(5, shippingAddress);
         pstmt.setString(6, shippingAddressSub);
         pstmt.setString(7, shippingPhone);
         pstmt.setString(8, shippingCellphone);
         pstmt.executeUpdate();
         pstmt.close();
         conn.close();
      } catch (Exception e) {
         System.out.println("배송지등록 중 오류!!");
         e.printStackTrace();
      }
   }

   // 배송지 삭제
   public void deleteShipping(String shippingName, String id) {
      try {
         conn = dataFactory.getConnection();
         String query = "delete from shippingaddrtbl where id=? and shippingName=?";
         System.out.println(query);
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, id);
         pstmt.setString(2, shippingName);
         pstmt.executeUpdate();
         pstmt.close();
         conn.close();
      } catch (Exception e) {
         System.out.println("배송지 삭제중 에러");
         e.printStackTrace();
      }
   }

   // 장바구니 조회
   public List<CartVO> cartSelect(String id) {
      List<CartVO> cartList = new ArrayList();
      try {
         conn = dataFactory.getConnection();
         System.out.println(id);
         String query = "select prodCode, cartCount, prodPrice, prodImage, prodName from carttbl where id=?";
         System.out.println(query);
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, id);
         ResultSet rs = pstmt.executeQuery();
         while (rs.next()) {
            int prodCode = rs.getInt("prodCode");
            int cartCount = rs.getInt("cartCount");
            int prodPrice = rs.getInt("prodPrice");
            String prodImage = rs.getString("prodImage");
            String prodName = rs.getString("prodName");
            System.out.println(prodCode);
            System.out.println(cartCount);
            System.out.println(prodPrice);
            System.out.println(prodImage);
            System.out.println(prodName);
            CartVO cartVo = new CartVO(prodCode, cartCount, prodPrice, prodImage, prodName);
            cartList.add(cartVo);
         }
         rs.close();
         pstmt.close();
         conn.close();
      } catch (Exception e) {
         System.out.println("장바구니조회중 에러");
         e.printStackTrace();
      }
      return cartList;
   }

   // 장바구니 삭제
   public void deleteCart(int prodCode, String id) {
      try {
         conn = dataFactory.getConnection();
         String query = "delete from carttbl where prodCode=? and id=?";
         System.out.println(query);
         pstmt = conn.prepareStatement(query);
         pstmt.setInt(1, prodCode);
         pstmt.setString(2, id);
         pstmt.executeUpdate();
         pstmt.close();
         conn.close();
      } catch (Exception e) {
         System.out.println("장바구니삭제중 에러");
         e.printStackTrace();
      }
   }

   // 장바구니 업데이트
   public void upadteCart(int cartCount, String id, String prodName1) {
      try {
         conn = dataFactory.getConnection();
         String query = "update carttbl set cartCount=? where id=? and prodName=?";
         System.out.println(query);
         pstmt = conn.prepareStatement(query);
         pstmt.setInt(1, cartCount);
         pstmt.setString(2, id);
         pstmt.setString(3, prodName1);
         pstmt.executeUpdate();
         pstmt.close();
         conn.close();
      } catch (Exception e) {
         System.out.println("장바구니 업데이트중 오류");
         e.printStackTrace();
      }
   }

   // 상품구매 리트스 가져오기
   public List<OrderVO> selectOrder1(String id) {
      List<OrderVO> orderList1 = new ArrayList();
      try {
         conn = dataFactory.getConnection();
         String query = "select orderCode,orderDate from ordertbl where id=? order by orderCode asc";
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, id);
         ResultSet rs = pstmt.executeQuery();
         while (rs.next()) {
            int orderCode = rs.getInt("orderCode");
            Date orderDate = rs.getDate("orderDate");
            OrderVO orderVo = new OrderVO();
            orderVo.setOrderCode(orderCode);
            orderVo.setOrderDate(orderDate);
            System.out.println(orderCode);
            System.out.println(orderDate);
            orderList1.add(orderVo);
         }
         rs.close();
         pstmt.close();
         conn.close();
      } catch (Exception e) {
         System.out.println("상품구매리스트1가져오던중에러");
         e.printStackTrace();
      }
      return orderList1;
   }

   // 상품상세구매조회
   // 상품상세구매조회
      public List<OrderDVO> selectOrder2(List<OrderVO> orderList1) {
         List<OrderDVO> orderList2 = new ArrayList();
         int[] orderCode = new int[orderList1.size()];
         if (orderList1.size() != 0) {
            for (int i = 0; i < orderList1.size(); i++) {
               orderCode[i] = orderList1.get(i).getOrderCode();
            }
         }
         try {
            conn = dataFactory.getConnection();
            String query = "SELECT o.prodCode, o.orderAmount, ot.orderDate, p.prodPrice, p.prodName FROM orderdtbl o INNER JOIN producttbl p ON o.prodCode = p.prodCode INNER JOIN ordertbl ot ON o.orderCode = ot.orderCode WHERE ot.orderCode = ?";
            for (int i = 1; i < orderCode.length; i++) {
               query += " or ot.orderCode=?";
            }
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, orderCode[0]);
            for (int i = 1; i < orderCode.length; i++) {
               pstmt.setInt(i + 1, orderCode[i]);
            }
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
               int prodCode = rs.getInt("prodCode");
               int orderAmount = rs.getInt("orderAmount");
               int prodPrice = rs.getInt("prodPrice");
               String prodName = rs.getString("prodName");
               Date orderDate=rs.getDate("orderDate");
               OrderDVO orderDvo = new OrderDVO();
               orderDvo.setProdCode(prodCode);
               orderDvo.setOrderAmount(orderAmount);
               orderDvo.setProdPrice(prodPrice);
               orderDvo.setProdName(prodName);
               orderDvo.setOrderDate(orderDate);
               System.out.println(prodCode);
               System.out.println(orderAmount);
               orderList2.add(orderDvo);
            }
            rs.close();
            pstmt.close();
            conn.close();
         } catch (Exception e) {
            System.out.println("상품구매리스트2가져오던중에러");
            e.printStackTrace();
         }
         return orderList2;
      }
   // 상품가격조회
   /*public List<ProductVO> selectOrder3(List<OrderDVO> orderList2) {
      List<ProductVO> orderList3 = new ArrayList();
      int[] prodCode = new int[orderList2.size()];
      for (int i = 0; i < prodCode.length; i++) {
         prodCode[i] = orderList2.get(i).getProdCode();
      }
      try {
         conn = dataFactory.getConnection();
         String query = "select prodPrice,prodName from producttbl where prodCode=?";
         for (int i = 1; i < prodCode.length; i++) {
            query += " or prodCode=?";
         }
         pstmt = conn.prepareStatement(query);
         pstmt.setInt(1, prodCode[0]);
         for (int i = 1; i < prodCode.length; i++) {
            pstmt.setInt(i + 1, prodCode[i]);
         }
         ResultSet rs = pstmt.executeQuery();
         while (rs.next()) {
            int prodPrice = rs.getInt("prodPrice");
            String prodName = rs.getString("prodName");
            ProductVO prodVo = new ProductVO();
            prodVo.setProdPrice(prodPrice);
            prodVo.setProdName(prodName);
            System.out.println(prodPrice);
            System.out.println(prodName);
            orderList3.add(prodVo);
         }
         rs.close();
         pstmt.close();
         conn.close();
      } catch (Exception e) {
         System.out.println("상품구매리스트3가져오던중에러");
         e.printStackTrace();
      }
      return orderList3;
   }*/
   
   // 마이페이지 자유게시판 글 가져오기
   public List selectFreePosts(Map<String, Integer> pagingMap, String id) {
      List free=new ArrayList<>();
      int section = pagingMap.get("section");
      int pageNum = pagingMap.get("pageNum");
      try {
         conn=dataFactory.getConnection();
         String query="SELECT * FROM (SELECT ROWNUM AS recNum, boardNo, boardName, writeDate from freeboardtbl where id=? order by writeDate desc) "
                    + "WHERE recNum BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10";
         pstmt=conn.prepareStatement(query);
         pstmt.setString(1, id);
         pstmt.setInt(2, section);
         pstmt.setInt(3, pageNum);
         pstmt.setInt(4, section);
         pstmt.setInt(5, pageNum);
         ResultSet rs=pstmt.executeQuery();
         while(rs.next()) {
            FreeBoardVO freeVO=new FreeBoardVO();
            freeVO.setBoardNo(rs.getInt("boardNo"));
            freeVO.setBoardName(rs.getString("boardName"));
            freeVO.setWriteDate(rs.getDate("writeDate"));
            
            free.add(freeVO);
         }
         rs.close();
         pstmt.close();
         conn.close();
      } catch (Exception e) {
         System.out.println("유저의 자유게시판 글 조회 중 오류 발생");
         e.printStackTrace();
      }
      return free;
   }
   
   // 자유게시판 글 개수
   public int selectUserTotFreePosts(String id) {
      int totCount = 0;
      try {
         conn = dataFactory.getConnection();
         String query = "select count(*) from freeboardtbl where id=?";
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, id);
         ResultSet rs = pstmt.executeQuery();
         if (rs.next()) { // 자료가 있을 경우
            totCount = rs.getInt(1); // 첫 번째 컬럼 값
         }
         rs.close();
         pstmt.close();
         conn.close();
      } catch (Exception e) {
         System.out.println("상품 전체 개수 조회 중 오류 발생");
         e.printStackTrace();
      }
      return totCount;
   }
   
   // 마이페이지 레시피 글 가져오기
   public List selectRecipePosts(String id) {
      List recipe=new ArrayList<>();
      try {
         conn=dataFactory.getConnection();
         String query="select recipeNo, recipeTitle, writeDate from recipetbl where id=?";
         pstmt=conn.prepareStatement(query);
         pstmt.setString(1, id);
         ResultSet rs=pstmt.executeQuery();
         while(rs.next()) {
            RecipeVO recVO=new RecipeVO();
            recVO.setRecipeNo(rs.getInt("recipeNo"));
            recVO.setRecipeTitle(rs.getString("recipeTitle"));
            recVO.setWriteDate(rs.getDate("writeDate"));
            
            recipe.add(recVO);
         }
         rs.close();
         pstmt.close();
         conn.close();
      } catch (Exception e) {
         System.out.println("유저의 레시피 글 조회 중 오류 발생");
         e.printStackTrace();
      }
      return recipe;
   }
   
   // 마이페이지 자유게시판 댓글 가져오기
   public List<FreeBoardReplyVO> selectFreeReply(Map<String, Integer> pagingMap, String id) {
      List free=new ArrayList<>();
      int section = pagingMap.get("section");
      int pageNum = pagingMap.get("pageNum");
      try {
         conn=dataFactory.getConnection();
         String query = "SELECT * FROM (SELECT ROWNUM AS recNum, boardNo, content, regDate "
               + "FROM freeboardreplytbl where id=? order by regDate desc) "
               + "WHERE recNum BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10";
         pstmt=conn.prepareStatement(query);
         pstmt.setString(1, id);
         pstmt.setInt(2, section);
         pstmt.setInt(3, pageNum);
         pstmt.setInt(4, section);
         pstmt.setInt(5, pageNum);
         ResultSet rs=pstmt.executeQuery();
         while(rs.next()) {
            FreeBoardReplyVO freeVO=new FreeBoardReplyVO();
            freeVO.setBoardNo(rs.getInt("boardNo"));
            freeVO.setContent(rs.getString("content"));
            freeVO.setRegDate(rs.getDate("regDate"));
            
            free.add(freeVO);
         }
         rs.close();
         pstmt.close();
         conn.close();
      } catch (Exception e) {
         System.out.println("유저의 자유게시판 댓글 조회 중 오류 발생");
         e.printStackTrace();
      }
      return free;
   }
   
   // 자유게시판 댓글 개수
   public int selectUserTotReplies(String id) {
      int totCount = 0;
      try {
         conn = dataFactory.getConnection();
         String query = "select count(*) from freeboardreplytbl where id=?";
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, id);
         ResultSet rs = pstmt.executeQuery();
         if (rs.next()) { // 자료가 있을 경우
            totCount = rs.getInt(1); // 첫 번째 컬럼 값
         }
         rs.close();
         pstmt.close();
         conn.close();
      } catch (Exception e) {
         System.out.println("상품 전체 개수 조회 중 오류 발생");
         e.printStackTrace();
      }
      return totCount;
   }
}