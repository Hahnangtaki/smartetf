import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './etf-product-my-suffix.reducer';
import { IEtfProductMySuffix } from 'app/shared/model/etf-product-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEtfProductMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEtfProductMySuffixUpdateState {
  isNew: boolean;
}

export class EtfProductMySuffixUpdate extends React.Component<IEtfProductMySuffixUpdateProps, IEtfProductMySuffixUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { etfProductEntity } = this.props;
      const entity = {
        ...etfProductEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/etf-product-my-suffix');
  };

  render() {
    const { etfProductEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="smartEtfApp.etfProduct.home.createOrEditLabel">Create or edit a EtfProduct</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : etfProductEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="etf-product-my-suffix-id">ID</Label>
                    <AvInput id="etf-product-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="productIdLabel" for="etf-product-my-suffix-productId">
                    Product Id
                  </Label>
                  <AvField
                    id="etf-product-my-suffix-productId"
                    type="text"
                    name="productId"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="productCodeLabel" for="etf-product-my-suffix-productCode">
                    Product Code
                  </Label>
                  <AvField
                    id="etf-product-my-suffix-productCode"
                    type="text"
                    name="productCode"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 10, errorMessage: 'This field cannot be longer than 10 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="productNameLabel" for="etf-product-my-suffix-productName">
                    Product Name
                  </Label>
                  <AvField
                    id="etf-product-my-suffix-productName"
                    type="text"
                    name="productName"
                    validate={{
                      maxLength: { value: 50, errorMessage: 'This field cannot be longer than 50 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="fundCategoryLabel" for="etf-product-my-suffix-fundCategory">
                    Fund Category
                  </Label>
                  <AvField
                    id="etf-product-my-suffix-fundCategory"
                    type="text"
                    name="fundCategory"
                    validate={{
                      maxLength: { value: 10, errorMessage: 'This field cannot be longer than 10 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="inceptionDateLabel" for="etf-product-my-suffix-inceptionDate">
                    Inception Date
                  </Label>
                  <AvField id="etf-product-my-suffix-inceptionDate" type="date" className="form-control" name="inceptionDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="clearingHouseLabel" for="etf-product-my-suffix-clearingHouse">
                    Clearing House
                  </Label>
                  <AvField
                    id="etf-product-my-suffix-clearingHouse"
                    type="text"
                    name="clearingHouse"
                    validate={{
                      maxLength: { value: 10, errorMessage: 'This field cannot be longer than 10 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="exchageLabel" for="etf-product-my-suffix-exchage">
                    Exchage
                  </Label>
                  <AvField
                    id="etf-product-my-suffix-exchage"
                    type="text"
                    name="exchage"
                    validate={{
                      maxLength: { value: 10, errorMessage: 'This field cannot be longer than 10 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="unitCreationLabel" for="etf-product-my-suffix-unitCreation">
                    Unit Creation
                  </Label>
                  <AvField
                    id="etf-product-my-suffix-unitCreation"
                    type="string"
                    className="form-control"
                    name="unitCreation"
                    validate={{
                      min: { value: 0, errorMessage: 'This field should be at least 0.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="unitPriceInitialLabel" for="etf-product-my-suffix-unitPriceInitial">
                    Unit Price Initial
                  </Label>
                  <AvField id="etf-product-my-suffix-unitPriceInitial" type="string" className="form-control" name="unitPriceInitial" />
                </AvGroup>
                <AvGroup>
                  <Label id="unitPriceCurrLabel" for="etf-product-my-suffix-unitPriceCurr">
                    Unit Price Curr
                  </Label>
                  <AvField id="etf-product-my-suffix-unitPriceCurr" type="string" className="form-control" name="unitPriceCurr" />
                </AvGroup>
                <AvGroup>
                  <Label id="lotPerBasketLabel" for="etf-product-my-suffix-lotPerBasket">
                    Lot Per Basket
                  </Label>
                  <AvField id="etf-product-my-suffix-lotPerBasket" type="string" className="form-control" name="lotPerBasket" />
                </AvGroup>
                <AvGroup>
                  <Label id="indexReffLabel" for="etf-product-my-suffix-indexReff">
                    Index Reff
                  </Label>
                  <AvField
                    id="etf-product-my-suffix-indexReff"
                    type="text"
                    name="indexReff"
                    validate={{
                      maxLength: { value: 10, errorMessage: 'This field cannot be longer than 10 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ratingLabel" for="etf-product-my-suffix-rating">
                    Rating
                  </Label>
                  <AvField id="etf-product-my-suffix-rating" type="string" className="form-control" name="rating" />
                </AvGroup>
                <AvGroup>
                  <Label id="feeLabel" for="etf-product-my-suffix-fee">
                    Fee
                  </Label>
                  <AvField id="etf-product-my-suffix-fee" type="string" className="form-control" name="fee" />
                </AvGroup>
                <AvGroup>
                  <Label id="miIdLabel" for="etf-product-my-suffix-miId">
                    Mi Id
                  </Label>
                  <AvField id="etf-product-my-suffix-miId" type="string" className="form-control" name="miId" />
                </AvGroup>
                <AvGroup>
                  <Label id="custodyIdLabel" for="etf-product-my-suffix-custodyId">
                    Custody Id
                  </Label>
                  <AvField id="etf-product-my-suffix-custodyId" type="string" className="form-control" name="custodyId" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/etf-product-my-suffix" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  etfProductEntity: storeState.etfProduct.entity,
  loading: storeState.etfProduct.loading,
  updating: storeState.etfProduct.updating,
  updateSuccess: storeState.etfProduct.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EtfProductMySuffixUpdate);
